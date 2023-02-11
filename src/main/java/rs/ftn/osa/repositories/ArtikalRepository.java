package rs.ftn.osa.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Artikal;

import java.util.List;

public interface ArtikalRepository extends ElasticsearchRepository<Artikal, String> {

    List<Artikal> findArtikalsByProdavac(String username);

    List<Artikal> findArtikalsByNazivContaining(String naziv);

    List<Artikal> findArtikalsByOpisContains(String tekstZaPretragu);

}
