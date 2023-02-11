package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.AkcijaDTO;
import rs.ftn.osa.model.entity.Akcija;
import rs.ftn.osa.service.interfaces.IAkcijaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/akcije")
public class AkcijaController {

    private final IAkcijaService akcijaService;

    public AkcijaController(IAkcijaService akcijaService) {
        this.akcijaService = akcijaService;
    }

    @GetMapping
    public ResponseEntity<List<AkcijaDTO>> getAkcije() {

        List<Akcija> akcije = akcijaService.findAll();

        List<AkcijaDTO> retVal = new ArrayList<>();
        for (Akcija akcija : akcije) {
            retVal.add(new AkcijaDTO(akcija));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AkcijaDTO> getAkcija(@PathVariable(name = "id") Long id) {

        Akcija akcija = akcijaService.findOne(id);
        if (akcija == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new AkcijaDTO(akcija), HttpStatus.OK);
    }

//    @PostMapping(consumes = "application/json")
//    public ResponseEntity<AkcijaDTO> createAkcija(@RequestBody AkcijaDTO akcijaDTO) {
//
//        Akcija akcija = new Akcija(akcijaDTO);
//        akcija.setArtikli(new HashSet<>()); //Izvuces iz baze
//        akcija.setProdavac(new Prodavac()); //Izvuces iz baze
//
//        return new ResponseEntity<>(new AkcijaDTO(akcija), HttpStatus.CREATED);
//
//    }

    @PutMapping(consumes = "application/json", value = "/{id}")
    public ResponseEntity<AkcijaDTO> editAkcija(@RequestBody AkcijaDTO akcijaDTO, @PathVariable(name = "id") Long id) {

        Akcija akcija = akcijaService.findOne(id);

        if (akcija == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        akcija.setProcenat(akcijaDTO.getProcenat());
        akcija.setOdKad(akcijaDTO.getOdKad());
        akcija.setDoKad(akcijaDTO.getDoKad());
        akcija.setTekst(akcijaDTO.getTekst());

        akcija = akcijaService.save(akcija);

        return new ResponseEntity<>(new AkcijaDTO(akcija), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteAkcija(@PathVariable(name = "id") Long id) {

        if (akcijaService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        akcijaService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
