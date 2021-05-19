package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.repositories.ProdavacRepository;
import rs.ftn.osa.service.IProdavacService;

import java.util.List;

@Service
public class ProdavacService implements IProdavacService {

    @Autowired
    private ProdavacRepository prodavacRepository;

    @Override
    public Prodavac findOne(Long id) {
        return prodavacRepository.getOne(id);
    }

    @Override
    public List<Prodavac> findAll() {
        return prodavacRepository.findAll();
    }

    @Override
    public Prodavac findByUsername(String username) {
        return prodavacRepository.findByUsername(username);
    }

    @Override
    public Prodavac save(Prodavac prodavac) {
        return prodavacRepository.save(prodavac);
    }

    @Override
    public void delete(Long id) {
        prodavacRepository.deleteById(id);
    }
}
