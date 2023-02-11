package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Administrator;
import rs.ftn.osa.repositories.AdministratorRepository;
import rs.ftn.osa.service.interfaces.IAdministratorService;

import java.util.List;

@Service
public class AdministratorService implements IAdministratorService {

    @Autowired
    private AdministratorRepository administratorRepository;

    @Override
    public Administrator findOne(Long id) {
        return administratorRepository.getOne(id);
    }

    @Override
    public List<Administrator> findAll() {
        return administratorRepository.findAll();
    }

    @Override
    public Administrator findByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }

    @Override
    public Administrator save(Administrator administrator) {
        return administratorRepository.save(administrator);
    }

    @Override
    public void delete(Long id) {
        administratorRepository.deleteById(id);
    }
}
