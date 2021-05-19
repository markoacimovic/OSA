package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Kupac;

public interface KupacRepository extends JpaRepository<Kupac, Long> {

    Kupac findByUsername(String username);
}
