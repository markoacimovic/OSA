package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Porudzbina;

import java.util.List;

public interface PorudzbinaRepository extends JpaRepository<Porudzbina, Long> {

    List<Porudzbina> getPorudzbinaByKupac_Username(String username);
}
