package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.ProdavacDTO;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.service.implementation.ProdavacService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(value = "/prodavci")
@CrossOrigin
public class ProdavacController {

    private final ProdavacService prodavacService;

    public ProdavacController(ProdavacService prodavacService) {
        this.prodavacService = prodavacService;
    }

    @GetMapping
    public ResponseEntity<List<ProdavacDTO>> getProdavce() {

        List<Prodavac> prodavci = prodavacService.findAll();
        List<ProdavacDTO> retVal = new ArrayList<>();

        for (Prodavac prodavac : prodavci) {
            retVal.add(new ProdavacDTO(prodavac));
        }

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdavacDTO> getProdavac(@PathVariable(name = "id") Long id) {

        Prodavac prodavac = prodavacService.findOne(id);
        if (prodavac == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProdavacDTO> createProdavac(@RequestBody ProdavacDTO prodavacDTO) {

        Prodavac prodavac = new Prodavac();
        prodavac.setIme(prodavacDTO.getIme());
        prodavac.setPrezime(prodavacDTO.getPrezime());
        prodavac.setUsername(prodavacDTO.getUsername());
        prodavac.setAdresa(prodavacDTO.getAdresa());
        prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
        prodavac.setEmail(prodavacDTO.getEmail());
        prodavac.setAkcije(new HashSet<>());
        prodavac.setArtikli("");
        prodavac.setBlokiran(false);

        prodavac = prodavacService.save(prodavac);

        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<ProdavacDTO> editProdavac(@RequestBody ProdavacDTO prodavacDTO, @PathVariable(name = "id") Long id) {

        Prodavac prodavac = prodavacService.findOne(id);
        if (prodavac == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prodavac.setIme(prodavacDTO.getIme());
        prodavac.setPrezime(prodavacDTO.getPrezime());
        prodavac.setAdresa(prodavacDTO.getAdresa());
        prodavac.setEmail(prodavacDTO.getEmail());
        prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
        prodavac.setUsername(prodavacDTO.getUsername());

        prodavac = prodavacService.save(prodavac);

        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('PRODAVAC')")
    @PutMapping(value = "/prodavac", consumes = "application/json")
    public ResponseEntity<ProdavacDTO> editUser(@RequestBody ProdavacDTO prodavacDTO, Principal principal){

        Prodavac prodavac = prodavacService.findByUsername(principal.getName());

        if(prodavac == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prodavac.setIme(prodavacDTO.getIme());
        prodavac.setPrezime(prodavacDTO.getPrezime());
        prodavac.setAdresa(prodavacDTO.getAdresa());
        prodavac.setEmail(prodavacDTO.getEmail());
        prodavac.setPoslujeOd(prodavacDTO.getPoslujeOd());
        prodavac.setUsername(prodavacDTO.getUsername());

        prodavac = prodavacService.save(prodavac);

        return new ResponseEntity<>(new ProdavacDTO(prodavac), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProdavac(@PathVariable(name = "id") Long id) {

        if (prodavacService.findOne(id) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        prodavacService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
