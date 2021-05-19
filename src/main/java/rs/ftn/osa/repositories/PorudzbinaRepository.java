package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Porudzbina;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {
}
