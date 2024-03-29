package rs.ftn.osa.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.BlockUserDTO;
import rs.ftn.osa.dto.KorisnikDTO;
import rs.ftn.osa.model.entity.Korisnik;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.service.interfaces.IAdministratorService;
import rs.ftn.osa.service.interfaces.IKupacService;
import rs.ftn.osa.service.interfaces.IProdavacService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/administratori")
@PreAuthorize("hasRole('ADMINISTRATOR')")
@CrossOrigin
public class AdministratorController {

    private final IAdministratorService administratorService;
    private final IProdavacService prodavacService;
    private final IKupacService kupacService;

    public AdministratorController(IKupacService kupacService, IAdministratorService administratorService, IProdavacService prodavacService){
        this.administratorService = administratorService;
        this.prodavacService = prodavacService;
        this.kupacService = kupacService;
    }


    @GetMapping("/all-users")
    public ResponseEntity<List<KorisnikDTO>> getAllUsers(){
        List<KorisnikDTO> korisnici = new ArrayList<>();

        List<Prodavac> prodavci = prodavacService.findAll();
        List<Kupac> kupci = kupacService.findAll();

        for (Korisnik korisnik : kupci) {
            korisnici.add(new KorisnikDTO(korisnik));
        }

        for (Korisnik korisnik : prodavci) {
            korisnici.add(new KorisnikDTO(korisnik));
        }

        return new ResponseEntity<>(korisnici, HttpStatus.OK);
    }

    @PostMapping(value = "/block")
    public ResponseEntity<Void> blockUser(@RequestBody BlockUserDTO blockUserDTO) {

        Kupac kupac;
        Prodavac prodavac;

        kupac = kupacService.findByUsername(blockUserDTO.getUsername());
        if (kupac != null) {
            kupac.setBlokiran(blockUserDTO.isBlokiran());
            kupacService.save(kupac);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        prodavac = prodavacService.findByUsername(blockUserDTO.getUsername());
        if (prodavac != null) {
            prodavac.setBlokiran(blockUserDTO.isBlokiran());
            prodavacService.save(prodavac);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
