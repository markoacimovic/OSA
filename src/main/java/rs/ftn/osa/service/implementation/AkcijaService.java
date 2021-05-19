package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Akcija;
import rs.ftn.osa.repositories.AkcijaRepository;
import rs.ftn.osa.service.IAkcijaService;

import java.util.List;

@Service
public class AkcijaService implements IAkcijaService {

    @Autowired
    private AkcijaRepository akcijaRepository;

    @Override
    public Akcija findOne(Long id) {
        return akcijaRepository.getOne(id);
    }

    @Override
    public List<Akcija> findAll() {
        return akcijaRepository.findAll();
    }

    @Override
    public Akcija save(Akcija akcija) {
        return akcijaRepository.save(akcija);
    }

    @Override
    public void delete(Long id) {
        akcijaRepository.deleteById(id);
    }
}
