package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.repositories.StavkaRepository;
import rs.ftn.osa.service.IStavkaService;

import java.util.List;

@Service
public class StavkaService implements IStavkaService {

    @Autowired
    private StavkaRepository stavkaRepository;

    @Override
    public Stavka findOne(Long id) {
        return stavkaRepository.getOne(id);
    }

    @Override
    public List<Stavka> findAll() {
        return stavkaRepository.findAll();
    }

    @Override
    public Stavka save(Stavka stavka) {
        return stavkaRepository.save(stavka);
    }

    @Override
    public void delete(Long id) {
        stavkaRepository.deleteById(id);
    }
}
