package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Stavka;

public interface StavkaRepository extends JpaRepository<Stavka, Long> {
}
