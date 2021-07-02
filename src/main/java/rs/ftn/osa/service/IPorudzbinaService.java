package rs.ftn.osa.service;

import rs.ftn.osa.model.entity.Porudzbina;

import java.util.List;

public interface IPorudzbinaService {

    Porudzbina findOne(Long id);

    List<Porudzbina> findPorudzbinaByKupac(String username);

    List<Porudzbina> findAll();

    Porudzbina save(Porudzbina porudzbina);

    void delete(Long id);
}
