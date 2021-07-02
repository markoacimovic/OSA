package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Artikal;

import java.util.List;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {

    List<Artikal> findArtikalsByProdavac_Username(String username);
}
