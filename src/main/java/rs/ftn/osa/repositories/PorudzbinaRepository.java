package rs.ftn.osa.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Porudzbina;

import java.util.List;

public interface PorudzbinaRepository extends ElasticsearchRepository<Porudzbina, String> {

    List<Porudzbina> findPorudzbinasByKupac(String username);

    List<Porudzbina> findPorudzbinasByKomentarContains(String text);

    List<Porudzbina> findPorudzbinasByDostavljenoAndKupac(Boolean isDostavljeno, String username);

}
