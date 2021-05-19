package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.repositories.PorudzbinaRepository;
import rs.ftn.osa.service.IPorudzbinaService;

import java.util.List;

@Service
public class PorudzbinaService implements IPorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Override
    public Porudzbina findOne(Long id) {
        return porudzbinaRepository.getOne(id);
    }

    @Override
    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    @Override
    public Porudzbina save(Porudzbina porudzbina) {
        return porudzbinaRepository.save(porudzbina);
    }

    @Override
    public void delete(Long id) {
        porudzbinaRepository.deleteById(id);
    }
}
