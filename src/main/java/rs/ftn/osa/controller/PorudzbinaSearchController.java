package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.PorudzbinaDTO;
import rs.ftn.osa.dto.SimpleQueryEsDTO;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.service.interfaces.IArtikalService;
import rs.ftn.osa.service.interfaces.IPorudzbinaService;
import rs.ftn.osa.service.interfaces.IProdavacService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/porudzbine-search")
@CrossOrigin
public class PorudzbinaSearchController {

    private final IPorudzbinaService porudzbinaService;
    private final IArtikalService artikalService;
    private final IProdavacService prodavacService;

    public PorudzbinaSearchController(IPorudzbinaService porudzbinaService, IArtikalService artikalService, IProdavacService prodavacService) {
        this.porudzbinaService = porudzbinaService;
        this.artikalService = artikalService;
        this.prodavacService = prodavacService;
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @PostMapping(value = "/komentar")
    public ResponseEntity<List<PorudzbinaDTO>> getByComment(@RequestBody SimpleQueryEsDTO simpleQueryEsDTO){

        List<Porudzbina> porudzbinas = porudzbinaService.findPorudzbinasByKomentar(simpleQueryEsDTO.getValue());
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        porudzbinas.forEach(porudzbina -> {
            retVal.add(new PorudzbinaDTO(porudzbina));
        });

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('KUPAC, PRODAVAC, ADMINISTRATOR')")
    @GetMapping(value = "/ocena")
    public ResponseEntity<List<PorudzbinaDTO>> getByPrice(@RequestParam(name = "min") Integer min, @RequestParam(name = "max") Integer max){

        List<Porudzbina> porudzbinas = porudzbinaService.findByRating(min, max);
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        porudzbinas.forEach(porudzbina -> {
            retVal.add(new PorudzbinaDTO(porudzbina));
        });

        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
}
