package rs.ftn.osa.service.interfaces;

import rs.ftn.osa.model.entity.Porudzbina;

import java.util.List;
import java.util.Optional;

public interface IPorudzbinaService {

    Optional<Porudzbina> findOne(String id);

    List<Porudzbina> findPorudzbinaByKupac(String username);

    List<Porudzbina> findPorudzbinasByKomentar(String text);

    List<Porudzbina> findByRating(Integer min, Integer max);

    List<Porudzbina> findByCena(Double min, Double max, String kupac);

    List<Porudzbina> findByDostavljene(String username, Boolean isDostavljeno);

    List<Porudzbina> findAll();

    Porudzbina save(Porudzbina porudzbina);

    void delete(String id);
}
