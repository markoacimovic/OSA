package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Prodavac;

public interface ProdavacRepository extends JpaRepository<Prodavac, Long> {

    Prodavac findByUsername(String username);
}
