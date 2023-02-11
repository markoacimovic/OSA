package rs.ftn.osa.service.interfaces;

import rs.ftn.osa.model.entity.Stavka;

import java.util.List;

public interface IStavkaService {

    Stavka findOne(Long id);

    List<Stavka> findAll();

    Stavka save(Stavka stavka);

    void delete(Long id);
}
