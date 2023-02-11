package rs.ftn.osa.service.implementation;

import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.repositories.StavkaRepository;
import rs.ftn.osa.service.interfaces.IStavkaService;

import java.util.List;

@Service
public class StavkaService implements IStavkaService {

    private final StavkaRepository stavkaRepository;

    public StavkaService(StavkaRepository stavkaRepository) {
        this.stavkaRepository = stavkaRepository;
    }

    @Override
    public Stavka findOne(Long id) {
        return null;
    }

    @Override
    public List<Stavka> findAll() {
        return null;
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
