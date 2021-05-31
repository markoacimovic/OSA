package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.PorudzbinaDTO;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.service.implementation.PorudzbinaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/porudzbina")
public class PorudzbinaController {

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping
    public ResponseEntity<List<PorudzbinaDTO>> getPorudzbine() {
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        for (Porudzbina porudzbina : porudzbine) {
            retVal.add(new PorudzbinaDTO(porudzbina));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> getPorudzbina(@PathVariable(name = "id") Long id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id);
        if (porudzbina == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PorudzbinaDTO> createPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO) {

        Porudzbina porudzbina = new Porudzbina(porudzbinaDTO);
        porudzbinaService.save(porudzbina);
        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> editPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO, @PathVariable(name = "id") Long id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id);
        if (porudzbina == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        porudzbina.setKomentar(porudzbinaDTO.getKomentar());
        porudzbina.setAnonimniKomentar(porudzbinaDTO.isAnonimniKomentar());
        porudzbina.setDostavljeno(porudzbinaDTO.isDostavljeno());
        porudzbina.setOcena(porudzbinaDTO.getOcena());
        porudzbina.setSatnica(porudzbinaDTO.getSatnica());

        porudzbina = porudzbinaService.save(porudzbina);

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePorudzbina(@PathVariable(name = "id") Long id) {

        if (porudzbinaService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        porudzbinaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
