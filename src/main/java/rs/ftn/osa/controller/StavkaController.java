package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.StavkaDTO;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.service.implementation.StavkaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stavke")
public class StavkaController {

    @Autowired
    private StavkaService stavkaService;

    @GetMapping
    public ResponseEntity<List<StavkaDTO>> getStavke() {

        List<Stavka> stavke = stavkaService.findAll();
        List<StavkaDTO> retVal = new ArrayList<>();

        for (Stavka stavka : stavke) {
            retVal.add(new StavkaDTO(stavka));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StavkaDTO> getStavka(@PathVariable(name = "id") Long id) {

        Stavka stavka = stavkaService.findOne(id);
        if (stavka == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new StavkaDTO(stavka), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StavkaDTO> createStavka(@RequestBody StavkaDTO stavkaDTO) {

        Stavka stavka = new Stavka(stavkaDTO);
        stavkaService.save(stavka);

        return new ResponseEntity<>(new StavkaDTO(stavka), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StavkaDTO> editStavka(@RequestBody StavkaDTO stavkaDTO, @PathVariable(name = "id") Long id) {

        Stavka stavka = stavkaService.findOne(id);
        if (stavka == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stavka.setKolicina(stavkaDTO.getKolicina());
        //dodaj pronalazenje

        return new ResponseEntity<>(new StavkaDTO(stavka), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteStavka(@PathVariable(name = "id") Long id) {

        if (stavkaService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stavkaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
