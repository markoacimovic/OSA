package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Artikal;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
}
