package rs.ftn.osa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ftn.osa.model.entity.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Administrator findByUsername(String username);
}

