package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.NarudzbinaDTO;
import rs.ftn.osa.dto.PorudzbinaDTO;
import rs.ftn.osa.dto.StavkaDTO;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.service.interfaces.IArtikalService;
import rs.ftn.osa.service.interfaces.IKupacService;
import rs.ftn.osa.service.interfaces.IPorudzbinaService;
import rs.ftn.osa.service.interfaces.IStavkaService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/stavke")
@CrossOrigin
public class StavkaController {

    private final IStavkaService stavkaService;
    private final IPorudzbinaService porudzbinaService;
    private final IArtikalService artikalService;
    private final IKupacService kupacService;

    public StavkaController(IStavkaService stavkaService, IPorudzbinaService porudzbinaService, IArtikalService artikalService, IKupacService kupacService) {
        this.stavkaService = stavkaService;
        this.porudzbinaService = porudzbinaService;
        this.artikalService = artikalService;
        this.kupacService = kupacService;
    }

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

//    @PostMapping
//    public ResponseEntity<StavkaDTO> createStavka(@RequestBody StavkaDTO stavkaDTO) {
//
//        Stavka stavka = new Stavka(stavkaDTO);
//        stavkaService.save(stavka);
//
//        return new ResponseEntity<>(new StavkaDTO(stavka), HttpStatus.CREATED);
//    }

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

    @PreAuthorize("hasRole('KUPAC')")
    @PostMapping(value = "/korpa", consumes = "application/json")
    public ResponseEntity<PorudzbinaDTO> cart(@RequestBody List<NarudzbinaDTO> korpaDTO, Principal principal){

        Porudzbina porudzbina = new Porudzbina();
        Stavka stavka;
        List<Stavka> stavke = new ArrayList<>();
        List<Artikal> artikli = new ArrayList<>();
        Kupac kupac = kupacService.findByUsername(principal.getName());

        if(kupac == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        porudzbina.setKupac(kupac.getUsername());
        porudzbina.setSatnica(new Date());
        porudzbina.setDostavljeno(false);

        porudzbina = porudzbinaService.save(porudzbina);
        for(NarudzbinaDTO narudzbinaDTO : korpaDTO){
            Artikal artikal = artikalService.findOne(narudzbinaDTO.getArtikalId());
            artikli.add(artikal);

            stavka = new Stavka();
            stavka.setArtikal(artikal.getId());
            stavka.setKolicina(Integer.parseInt(narudzbinaDTO.getKolicina()));
            stavka.setPorudzbina(porudzbina.getId());
            stavka = stavkaService.save(stavka);
            stavke.add(stavka);
        }

        porudzbina.setStavke(new HashSet<>(stavke));
        porudzbina = porudzbinaService.save(porudzbina);

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), HttpStatus.OK);
    }
}
