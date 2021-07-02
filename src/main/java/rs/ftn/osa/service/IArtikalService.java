package rs.ftn.osa.service;

import rs.ftn.osa.model.entity.Artikal;

import java.util.List;

public interface IArtikalService {

    Artikal findOne(Long id);

    List<Artikal> findAll();

    List<Artikal> findAllByProdavac(String username);

    Artikal save(Artikal artikal);

    void delete(Long id);
}
