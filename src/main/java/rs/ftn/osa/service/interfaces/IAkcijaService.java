package rs.ftn.osa.service.interfaces;

import rs.ftn.osa.model.entity.Akcija;

import java.util.List;

public interface IAkcijaService {

    Akcija findOne(Long id);

    List<Akcija> findAll();

    Akcija save(Akcija akcija);

    void delete(Long id);
}
