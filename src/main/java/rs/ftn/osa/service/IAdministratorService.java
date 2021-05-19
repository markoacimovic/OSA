package rs.ftn.osa.service;

import rs.ftn.osa.model.entity.Administrator;

import java.util.List;

public interface IAdministratorService {

    Administrator findOne(Long id);

    List<Administrator> findAll();

    Administrator findByUsername(String username);

    Administrator save(Administrator administrator);

    void delete(Long id);
}
