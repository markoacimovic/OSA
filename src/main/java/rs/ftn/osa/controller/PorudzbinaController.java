package rs.ftn.osa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.KomentariDTO;
import rs.ftn.osa.dto.PorudzbinaDTO;
import rs.ftn.osa.model.entity.Artikal;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.model.entity.Stavka;
import rs.ftn.osa.service.interfaces.IArtikalService;
import rs.ftn.osa.service.interfaces.IPorudzbinaService;
import rs.ftn.osa.service.interfaces.IProdavacService;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/porudzbine")
@CrossOrigin
public class PorudzbinaController {

    private final IPorudzbinaService porudzbinaService;
    private final IArtikalService artikalService;
    private final IProdavacService prodavacService;

    public PorudzbinaController(IPorudzbinaService porudzbinaService, IArtikalService artikalService, IProdavacService prodavacService) {
        this.porudzbinaService = porudzbinaService;
        this.artikalService = artikalService;
        this.prodavacService = prodavacService;
    }

    @GetMapping
    public ResponseEntity<List<PorudzbinaDTO>> getPorudzbine() {
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        for (Porudzbina porudzbina : porudzbine) {
            retVal.add(new PorudzbinaDTO(porudzbina));
        }

        return new ResponseEntity<>(retVal, OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> getPorudzbina(@PathVariable(name = "id") String id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id).orElse(null);
        if (porudzbina == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), OK);
    }
//
//    @PostMapping
//    public ResponseEntity<PorudzbinaDTO> createPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO) {
//
//        Porudzbina porudzbina = new Porudzbina(porudzbinaDTO);
//        porudzbinaService.save(porudzbina);
//        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), CREATED);
//    }

    @PreAuthorize("hasRole('KUPAC')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> editPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO, @PathVariable(name = "id") String id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id).get();
        if (porudzbina == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        for (Stavka stavka:porudzbina.getStavke()) {
            Artikal artikal = artikalService.findOne(stavka.getArtikal());
            if(artikal.getBrojKomentara() == null){
                artikal.setBrojKomentara(1);
            }else {
                artikal.setBrojKomentara(artikal.getBrojKomentara() + 1);
            }
            if(artikal.getOcena() == null){
                artikal.setOcena((1.0 + porudzbina.getOcena())/2);
            }else {
                artikal.setOcena((artikal.getOcena() + porudzbinaDTO.getOcena())/2);
            }
            artikalService.save(artikal);
        }

        porudzbina.setKomentar(porudzbinaDTO.getKomentar());
        porudzbina.setAnonimniKomentar(porudzbinaDTO.isAnonimniKomentar());
        porudzbina.setDostavljeno(porudzbinaDTO.isDostavljeno());
        porudzbina.setOcena(porudzbinaDTO.getOcena());
        porudzbina.setSatnica(porudzbinaDTO.getSatnica());

        porudzbina = porudzbinaService.save(porudzbina);

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePorudzbina(@PathVariable(name = "id") String id) {

        if (porudzbinaService.findOne(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        porudzbinaService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @PreAuthorize("hasRole('KUPAC')")
    @GetMapping(value = "/porudzbine-kupca")
    public ResponseEntity<List<PorudzbinaDTO>> getPorudzbineKupca(Principal principal) {

        PorudzbinaDTO porudzbinaDTO;
        if (principal.getName() == null) {
            return new ResponseEntity<>(BAD_REQUEST);
        }

        List<Porudzbina> porudzbine = porudzbinaService.findPorudzbinaByKupac(principal.getName());
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        for (Porudzbina porudzbina : porudzbine) {
            if (porudzbina.isDostavljeno() == false) {
                porudzbinaDTO = new PorudzbinaDTO(porudzbina);
                Artikal artikal = artikalService.findOne(porudzbina.getStavke().iterator().next().getArtikal());
                Prodavac prodavac = prodavacService.findByUsername(artikal.getProdavac());
                //TODO Izmeni da se prikazuje naziv i namesti da dobijes prodavca
                porudzbinaDTO.setProdavac(prodavac.getNaziv());
                retVal.add(porudzbinaDTO);
            }
        }

        return new ResponseEntity<>(retVal, OK);
    }

    @PreAuthorize("hasRole('KUPAC')")
    @GetMapping(value = "/dostavljene-porudzbine-kupca")
    public ResponseEntity<List<PorudzbinaDTO>> getDostavljenePorudzbineKupca(Principal principal) {

        if (principal.getName() == null) {
            return new ResponseEntity<>(BAD_REQUEST);
        }

        List<Porudzbina> porudzbine = porudzbinaService.findByDostavljene(principal.getName(), true);
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        porudzbine.forEach(porudzbina -> {
            PorudzbinaDTO porudzbinaDTO = new PorudzbinaDTO(porudzbina);
            Artikal artikal = artikalService.findOne(porudzbina.getStavke().iterator().next().getArtikal());
            Prodavac prodavac = prodavacService.findByUsername(artikal.getProdavac());
            //TODO Izmeni da se prikazuje naziv i namesti da dobijes prodavca
            porudzbinaDTO.setProdavac(prodavac.getNaziv());
            retVal.add(porudzbinaDTO);
        });

        return new ResponseEntity<>(retVal, OK);
    }

    @PermitAll
    @GetMapping(value = "/komentari/{username}")
    public ResponseEntity<List<KomentariDTO>> getKomentari(@PathVariable("username") String username) {
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<KomentariDTO> komentari = new ArrayList<>();
        KomentariDTO komentariDTO;

        for (Porudzbina porudzbina : porudzbine) {
            //TODO Izmeni da se prikazuje naziv i namesti da dobijes prodavca
            Artikal artikal = artikalService.findOne(porudzbina.getStavke().iterator().next().getArtikal());
            String prodavac = artikal.getProdavac();
            if (prodavac.equals(username)) {
                komentariDTO = new KomentariDTO();
                komentariDTO.setKomentar(porudzbina.getKomentar());
                komentariDTO.setUsername(porudzbina.getKupac());
                komentariDTO.setAnonimno(porudzbina.isAnonimniKomentar());
                komentari.add(komentariDTO);
            }
        }

        return new ResponseEntity<>(komentari, OK);
    }
}
