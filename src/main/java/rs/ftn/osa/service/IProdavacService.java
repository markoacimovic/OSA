package rs.ftn.osa.service;

import rs.ftn.osa.model.entity.Prodavac;

import java.util.List;

public interface IProdavacService {

    Prodavac findOne(Long id);

    List<Prodavac> findAll();

    Prodavac findByUsername(String username);

    Prodavac save(Prodavac prodavac);

    void delete(Long id);
}
