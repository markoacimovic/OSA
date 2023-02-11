package rs.ftn.osa.service.interfaces;

import rs.ftn.osa.dto.ArtikalBackendDTO;
import rs.ftn.osa.model.entity.Artikal;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface IArtikalService {

    Artikal findOne(String id);

    List<Artikal> findByNaziv(String naziv);

    List<Artikal> findAll();

    List<Artikal> findAllByProdavac(String username);

    List<Artikal> findByOpis(String text);

    List<Artikal> findByPrice(Double min, Double max);

    List<Artikal> findByPriceAndNaziv(Double min, Double max,  String naziv);

    List<Artikal> findByProsecnaOcena(Integer min, Integer max);

    List<Artikal> findByBrojKomentara(Integer min, Integer max);

    List<Artikal> findByPriceOrOpis(Double min, Double max,  String naziv);

    void reindex();

    int indexUnitFromFile(File file);

    void indexUploadedFile(ArtikalBackendDTO artikalBackendDTO,String prodavac) throws IOException;

    File getResourceFilePath(String path);

    Artikal save(Artikal artikal);

    void delete(String id);
}
