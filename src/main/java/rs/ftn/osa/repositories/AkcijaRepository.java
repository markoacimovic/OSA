package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Akcija;

public interface AkcijaRepository extends JpaRepository<Akcija, Long> {
}
