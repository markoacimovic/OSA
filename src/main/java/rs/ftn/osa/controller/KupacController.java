package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.model.dto.KupacDTO;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.service.implementation.KupacService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/kupac")
public class KupacController {

    @Autowired
    private KupacService kupacService;

    @GetMapping
    public ResponseEntity<List<KupacDTO>> getKupci() {

        List<Kupac> kupci = kupacService.findAll();
        List<KupacDTO> retVal = new ArrayList<>();

        for (Kupac kupac : kupci) {
            retVal.add(new KupacDTO(kupac));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<KupacDTO> getKupac(@PathVariable(name = "id") Long id) {

        Kupac kupac = kupacService.findOne(id);
        if (kupac == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new KupacDTO(kupac), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<KupacDTO> createKupac(@RequestBody KupacDTO kupacDTO) {

        Kupac kupac = new Kupac(kupacDTO);
        kupac.setPorudzbine(new HashSet<>());
        kupac.setBlokiran(false);
        kupacService.save(kupac);

        return new ResponseEntity<>(new KupacDTO(kupac), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<KupacDTO> editKupac(@RequestBody KupacDTO kupacDTO, @PathVariable(name = "id") Long id) {

        Kupac kupac = kupacService.findOne(id);

        if (kupac == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        kupac.setIme(kupacDTO.getIme());
        kupac.setAdresa(kupacDTO.getAdresa());
        kupac.setPrezime(kupac.getPrezime());
        kupac.setUsername(kupac.getUsername());

        kupac = kupacService.save(kupac);
        return new ResponseEntity<>(new KupacDTO(kupac), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteKupac(@PathVariable(name = "id") Long id) {

        if (kupacService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        kupacService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}