package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.repositories.KupacRepository;
import rs.ftn.osa.service.IKupacService;

import java.util.List;

@Service
public class KupacService implements IKupacService {

    @Autowired
    private KupacRepository kupacRepository;

    @Override
    public Kupac findOne(Long id) {
        return kupacRepository.getOne(id);
    }

    @Override
    public List<Kupac> findAll() {
        return kupacRepository.findAll();
    }

    @Override
    public Kupac findByUsername(String username) {
        return kupacRepository.findByUsername(username);
    }

    @Override
    public Kupac save(Kupac kupac) {
        return kupacRepository.save(kupac);
    }

    @Override
    public void delete(Long id) {
        kupacRepository.deleteById(id);
    }
}
