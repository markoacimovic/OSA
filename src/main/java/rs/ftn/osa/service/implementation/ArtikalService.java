package rs.ftn.osa.service.implementation;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import rs.ftn.osa.dto.ArtikalBackendDTO;
import rs.ftn.osa.dto.KomentariDTO;
import rs.ftn.osa.dto.SimpleQueryEsDTO;
import rs.ftn.osa.lucene.indexing.handlers.*;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.repositories.ArtikalRepository;
import rs.ftn.osa.repositories.PorudzbinaRepository;
import rs.ftn.osa.service.interfaces.IArtikalService;
import rs.ftn.osa.support.SearchQueryGenerator;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
public class ArtikalService implements IArtikalService {

    @Value("${files.path}")
    private String dataFilesPath;

    @Value("${app.upload.dir:${user.home}}")
    public String uploadDir;

    private final ArtikalRepository artikalRepository;
    private final PorudzbinaRepository porudzbinaRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public ArtikalService(ArtikalRepository artikalRepository, PorudzbinaRepository porudzbinaRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.artikalRepository = artikalRepository;
        this.porudzbinaRepository = porudzbinaRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public Artikal findOne(String id) {
        return artikalRepository.findById(id).get();
    }

    @Override
    public List<Artikal> findByNaziv(String naziv) {
        return artikalRepository.findArtikalsByNazivContaining(naziv);
    }

    @Override
    public List<Artikal> findAllByProdavac(String username) {
        return artikalRepository.findArtikalsByProdavac(username);
    }

    @Override
    public List<Artikal> findAll() {
        return ((List) artikalRepository.findAll());
    }

    @Override
    public List<Artikal> findByOpis(String text) {

        QueryBuilder opisQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEsDTO("opis", text));
        return getArtikalsFromSearchHit(searchQuery(opisQuery));
    }

    @Override
    public List<Artikal> findByPrice(Double min, Double max) {

        String range = min + "-" + max;
        QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("cena", range));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(priceQuery);
        return getArtikalsFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Artikal> findByPriceAndNaziv(Double min, Double max,  String naziv) {

        String range = min + "-" + max;
        QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("cena", range));
        QueryBuilder nazivQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEsDTO("naziv", naziv));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(nazivQuery)
                .must(priceQuery);
        return getArtikalsFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Artikal> findByPriceOrOpis(Double min, Double max,  String opis) {

        String range = min + "-" + max;
        QueryBuilder priceQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("cena", range));
        QueryBuilder opisQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEsDTO("opis", opis));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .should(opisQuery)
                .should(priceQuery);
        return getArtikalsFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Artikal> findByProsecnaOcena(Integer min, Integer max) {
        String range = min + "-" + max;
        QueryBuilder reviewQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("ocena", range));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(reviewQuery);

        return getArtikalsFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Artikal> findByBrojKomentara(Integer min, Integer max) {
        String range = min + "-" + max;
        QueryBuilder reviewQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("brojKomentara", range));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(reviewQuery);

        return getArtikalsFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public Artikal save(Artikal artikal) {
        return artikalRepository.save(artikal);
    }

    @Override
    public void delete(String id) {
        artikalRepository.deleteById(id);
    }

//    private SearchHits<Artikal> searchQuery(BoolQueryBuilder boolQueryBuilder) {
//        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
//                .withQuery(boolQueryBuilder)
//                .build();
//
//        return elasticsearchRestTemplate.search(searchQuery, Artikal.class,  IndexCoordinates.of("artikli"));
//    }


    @Override
    public void reindex() {
        File dataDir = getResourceFilePath(dataFilesPath);
        indexUnitFromFile(dataDir);
    }

    @Override
    public int indexUnitFromFile(File file) {
        DocumentHandler handler;
        String fileName;
        int retVal = 0;
        try {
            File[] files;
            if(file.isDirectory()){
                files = file.listFiles();
            }else{
                files = new File[1];
                files[0] = file;
            }
            assert files != null;
            for(File newFile : files){
                if(newFile.isFile()){
                    fileName = newFile.getName();
                    handler = getHandler(fileName);
                    if(handler == null){
                        System.out.println("Nije moguce indeksirati dokument sa nazivom: " + fileName);
                        continue;
                    }
                    save(handler.getIndexUnit(newFile));
                    retVal++;
                } else if (newFile.isDirectory()){
                    retVal += indexUnitFromFile(newFile);
                }
            }
            System.out.println("indexing done");
        } catch (Exception e) {
            System.out.println("indexing NOT done");
        }
        return retVal;
    }

    public DocumentHandler getHandler(String fileName){
        return getDocumentHandler(fileName);
    }

    public static DocumentHandler getDocumentHandler(String fileName) {
        if(fileName.endsWith(".txt")){
            return new TextDocHandler();
        }else if(fileName.endsWith(".pdf")){
            return new PDFHandler();
        }else if(fileName.endsWith(".doc")){
            return new WordHandler();
        }else if(fileName.endsWith(".docx")){
            return new Word2007Handler();
        }else{
            return null;
        }
    }

    @Override
    public void indexUploadedFile(ArtikalBackendDTO artikalBackendDTO, String prodavac) throws IOException {

//        for (MultipartFile file : artikalBackendDTO.getFiles()) {
//            if (file.isEmpty()) {
//                continue;
//            }
//
//            String fileName = saveUploadedFileInFolder(file);
//            if(fileName != null){
//                Artikal artikalIndexUnit = getHandler(fileName).getIndexUnit(new File(fileName));
//                artikalIndexUnit.setProdavac(prodavac);
//                artikalIndexUnit.setCena(artikalBackendDTO.getCena());
//                artikalIndexUnit.setNaziv(artikalBackendDTO.getNaziv());
//                save(artikalIndexUnit);
//            }
//        }
    }

    @Override
    public File getResourceFilePath(String path) {
        URL url = this.getClass().getClassLoader().getResource(path);
        File file = null;
        try {
            if(url != null) {
                file = new File(url.toURI());
            }
        } catch (URISyntaxException e) {
            file = new File(url.getPath());
        }
        return file;
    }

    private String saveUploadedFileInFolder(MultipartFile file) throws IOException {
        String retVal = null;
        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(getResourceFilePath(dataFilesPath).getAbsolutePath() + File.separator + file.getOriginalFilename());
            Files.write(path, bytes);
            Path filepath = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename())));
            Files.write(filepath, bytes);
            retVal = path.toString();
        }
        return retVal;
    }

    private SearchHits<Artikal> searchQuery(QueryBuilder queryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, Artikal.class,  IndexCoordinates.of("artikli"));
    }



    private List<Artikal> getArtikalsFromSearchHit(SearchHits<Artikal> searchHits){
        return searchHits.map(artikal -> artikal.getContent()).toList();
    }
}
