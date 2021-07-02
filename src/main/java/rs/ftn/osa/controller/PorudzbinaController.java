package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.KomentariDTO;
import rs.ftn.osa.dto.PorudzbinaDTO;
import rs.ftn.osa.model.entity.Porudzbina;
import rs.ftn.osa.service.IPorudzbinaService;
import rs.ftn.osa.service.implementation.PorudzbinaService;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping(value = "/porudzbine")
@CrossOrigin
public class PorudzbinaController {

    @Autowired
    private IPorudzbinaService porudzbinaService;

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
    public ResponseEntity<PorudzbinaDTO> getPorudzbina(@PathVariable(name = "id") Long id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id);
        if (porudzbina == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), OK);
    }

    @PostMapping
    public ResponseEntity<PorudzbinaDTO> createPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO) {

        Porudzbina porudzbina = new Porudzbina(porudzbinaDTO);
        porudzbinaService.save(porudzbina);
        return new ResponseEntity<>(new PorudzbinaDTO(porudzbina), CREATED);
    }

    @PreAuthorize("hasRole('KUPAC')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<PorudzbinaDTO> editPorudzbina(@RequestBody PorudzbinaDTO porudzbinaDTO, @PathVariable(name = "id") Long id) {

        Porudzbina porudzbina = porudzbinaService.findOne(id);
        if (porudzbina == null) {
            return new ResponseEntity<>(NOT_FOUND);
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
    public ResponseEntity<Void> deletePorudzbina(@PathVariable(name = "id") Long id) {

        if (porudzbinaService.findOne(id) == null) {
            return new ResponseEntity<>(NOT_FOUND);
        }

        porudzbinaService.delete(id);
        return new ResponseEntity<>(OK);
    }

    @PreAuthorize("hasRole('KUPAC')")
    @GetMapping(value = "/porudzbine-kupca")
    public ResponseEntity<List<PorudzbinaDTO>> getPorudzbineKupca(Principal principal){

        PorudzbinaDTO porudzbinaDTO;
        if(principal.getName() == null){
            return new ResponseEntity<>(BAD_REQUEST);
        }

        List<Porudzbina> porudzbine = porudzbinaService.findPorudzbinaByKupac(principal.getName());
        List<PorudzbinaDTO> retVal = new ArrayList<>();

        for(Porudzbina porudzbina : porudzbine){
            if(porudzbina.isDostavljeno() == false){
                porudzbinaDTO = new PorudzbinaDTO(porudzbina);
                porudzbinaDTO.setProdavac(porudzbina.getStavke().iterator().next().getArtikal().getProdavac().getNaziv());
                retVal.add(porudzbinaDTO);
            }
        }

        return new ResponseEntity<>(retVal, OK);
    }

    @PermitAll
    @GetMapping(value = "/komentari/{username}")
    public ResponseEntity<List<KomentariDTO>> getKomentari(@PathVariable("username") String username){
        List<Porudzbina> porudzbine = porudzbinaService.findAll();
        List<KomentariDTO> komentari = new ArrayList<>();
        KomentariDTO komentariDTO;

        for (Porudzbina porudzbina : porudzbine) {
            String prodavac = porudzbina.getStavke().iterator().next().getArtikal().getProdavac().getUsername();
            if(prodavac.equals(username)){
                komentariDTO = new KomentariDTO();
                komentariDTO.setKomentar(porudzbina.getKomentar());
                komentariDTO.setUsername(porudzbina.getKupac().getUsername());
                komentariDTO.setAnonimno(porudzbina.isAnonimniKomentar());
                komentari.add(komentariDTO);
            }
        }

        return new ResponseEntity<>(komentari, OK);
    }
}
