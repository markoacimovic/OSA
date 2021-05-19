package rs.ftn.osa.service;

import rs.ftn.osa.model.entity.Kupac;

import java.util.List;

public interface IKupacService {

    Kupac findOne(Long id);

    List<Kupac> findAll();

    Kupac findByUsername(String username);

    Kupac save(Kupac kupac);

    void delete(Long id);
}
