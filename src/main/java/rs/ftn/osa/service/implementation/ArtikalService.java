package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.repositories.ArtikalRepository;
import rs.ftn.osa.service.IArtikalService;

import java.util.List;

@Service
public class ArtikalService implements IArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    @Override
    public Artikal findOne(Long id) {
        return artikalRepository.getOne(id);
    }

    @Override
    public List<Artikal> findAll() {
        return artikalRepository.findAll();
    }

    @Override
    public Artikal save(Artikal artikal) {
        return artikalRepository.save(artikal);
    }

    @Override
    public void delete(Long id) {
        artikalRepository.deleteById(id);
    }
}
