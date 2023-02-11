package rs.ftn.osa.service.implementation;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import rs.ftn.osa.dto.SimpleQueryEsDTO;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.repositories.PorudzbinaRepository;
import rs.ftn.osa.repositories.StavkaRepository;
import rs.ftn.osa.service.interfaces.IPorudzbinaService;
import rs.ftn.osa.support.SearchQueryGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PorudzbinaService implements IPorudzbinaService {

    private final PorudzbinaRepository porudzbinaRepository;
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public PorudzbinaService(PorudzbinaRepository porudzbinaRepository, ElasticsearchRestTemplate elasticsearchRestTemplate) {
        this.porudzbinaRepository = porudzbinaRepository;
        this.elasticsearchRestTemplate = elasticsearchRestTemplate;
    }

    @Override
    public Optional<Porudzbina> findOne(String id) {
        return porudzbinaRepository.findById(id);
    }

    @Override
    public List<Porudzbina> findByDostavljene(String username, Boolean isDostavljeno) {
        return porudzbinaRepository.findPorudzbinasByDostavljenoAndKupac(isDostavljeno, username);
    }

    @Override
    public List<Porudzbina> findPorudzbinaByKupac(String username) {
        return porudzbinaRepository.findPorudzbinasByKupac(username);
    }

    @Override
    public List<Porudzbina> findPorudzbinasByKomentar(String text) {
        QueryBuilder matchQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEsDTO("komentar", text));
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery().must(matchQuery);

        return getPorudzbinasFromSearchHit(searchQuery(boolQueryBuilder));
    }

    @Override
    public List<Porudzbina> findByRating(Integer min, Integer max) {

        String range = min + "-" + max;
        QueryBuilder ratingQuery = SearchQueryGenerator.createRangeQueryBuilder(new SimpleQueryEsDTO("ocena", range));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(ratingQuery);
        return getPorudzbinasFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Porudzbina> findByCena(Double min, Double max, String kupac) {

        QueryBuilder userQuery = SearchQueryGenerator.createMatchQueryBuilder(new SimpleQueryEsDTO("kupac", kupac));

        BoolQueryBuilder boolQueryPrice = QueryBuilders
                .boolQuery()
                .must(userQuery);

        List<Porudzbina> porudzbinas = getPorudzbinasFromSearchHit(searchQuery(boolQueryPrice));
        List<Stavka> stavkas = new ArrayList<>();

        porudzbinas.forEach(porudzbina -> stavkas.addAll(porudzbina.getStavke()));


        return getPorudzbinasFromSearchHit(searchQuery(boolQueryPrice));
    }

    @Override
    public List<Porudzbina> findAll() {

        List<Porudzbina> porudzbinas = new ArrayList<>();
        porudzbinaRepository.findAll().forEach(porudzbina -> porudzbinas.add(porudzbina));

        return porudzbinas;
    }

    @Override
    public Porudzbina save(Porudzbina porudzbina) {
        return porudzbinaRepository.save(porudzbina);
    }

    @Override
    public void delete(String id) {
        porudzbinaRepository.deleteById(id);
    }

    private SearchHits<Porudzbina> searchQuery(QueryBuilder queryBuilder) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryBuilder)
                .build();

        return elasticsearchRestTemplate.search(searchQuery, Porudzbina.class,  IndexCoordinates.of("porudzbine"));
    }

    private List<Porudzbina> getPorudzbinasFromSearchHit(SearchHits<Porudzbina> searchQuery) {

        return searchQuery.map(porudzbinaSearchHit -> porudzbinaSearchHit.getContent()).toList();
    }
}
