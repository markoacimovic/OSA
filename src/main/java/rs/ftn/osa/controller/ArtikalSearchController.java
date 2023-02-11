package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.ArtikalDTO;
import rs.ftn.osa.dto.SimpleQueryEsDTO;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.security.TokenUtils;
import rs.ftn.osa.service.implementation.ArtikalService;
import rs.ftn.osa.service.implementation.ProdavacService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/artikli-search")
@CrossOrigin
public class ArtikalSearchController {

    private final ArtikalService artikalService;
    private final ProdavacService prodavacService;
    private final TokenUtils tokenUtils;

    public ArtikalSearchController(ArtikalService artikalService, ProdavacService prodavacService, TokenUtils tokenUtils) {
        this.artikalService = artikalService;
        this.prodavacService = prodavacService;
        this.tokenUtils = tokenUtils;
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR, ANONYMOUS')")
    @PostMapping("/naziv")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalNaziv(@RequestBody SimpleQueryEsDTO simpleQueryEsDTO){

        List<Artikal> artikals = artikalService.findByNaziv(simpleQueryEsDTO.getValue());
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR, ANONYMOUS')")
    @PostMapping("/opis")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalOpis(@RequestBody SimpleQueryEsDTO simpleQueryEsDTO){

        List<Artikal> artikals = artikalService.findByOpis(simpleQueryEsDTO.getValue());
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping("/cena")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalCena(@RequestParam(name = "min") Double min, @RequestParam(name = "max") Double max){

        List<Artikal> artikals = artikalService.findByPrice(min, max);
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @PostMapping("/multisearch")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalCenaAndNazivAndOpis(@RequestParam(name = "min") Double min, @RequestParam(name = "max") Double max,
                                                                             @RequestBody SimpleQueryEsDTO simpleQueryEsDTO){

        List<Artikal> artikals = artikalService.findByPriceAndNaziv(min, max, simpleQueryEsDTO.getValue());
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping("/ocena")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalOcena(@RequestParam(name = "min") Integer min, @RequestParam(name = "max") Integer max){

        List<Artikal> artikals = artikalService.findByProsecnaOcena(min, max);
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping("/komentar")
    public ResponseEntity<List<ArtikalDTO>> searchArtikalKomentar(@RequestParam(name = "min") Integer min, @RequestParam(name = "max") Integer max){

        List<Artikal> artikals = artikalService.findByBrojKomentara(min, max);
        List<ArtikalDTO> retVal = new ArrayList<>();

        artikals.forEach(artikal -> retVal.add(
                new ArtikalDTO(artikal)
        ));

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

}
