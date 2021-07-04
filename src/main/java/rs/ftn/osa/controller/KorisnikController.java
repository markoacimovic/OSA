package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.UserInfoDTO;
import rs.ftn.osa.model.entity.Administrator;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.service.IAdministratorService;
import rs.ftn.osa.service.IKupacService;
import rs.ftn.osa.service.IProdavacService;

import java.security.Principal;

@RestController
@RequestMapping("/korisnici")
@CrossOrigin
public class KorisnikController {

    @Autowired
    private IKupacService kupacService;

    @Autowired
    private IProdavacService prodavacService;

    @Autowired
    private IAdministratorService administratorService;

    @PreAuthorize("hasAnyRole('ADMINISTRATOR, KUPAC, PRODAVAC')")
    @GetMapping("/korisnik")
    public ResponseEntity<?> getUser(Principal principal){

        Kupac kupac;
        Prodavac prodavac;
        Administrator administrator;

        kupac = kupacService.findByUsername(principal.getName());
        if(kupac != null){
            return new ResponseEntity<>(new UserInfoDTO(kupac), HttpStatus.OK);
        }

        prodavac = prodavacService.findByUsername(principal.getName());
        if(prodavac != null){
            return new ResponseEntity<>(new UserInfoDTO(prodavac), HttpStatus.OK);
        }

        administrator = administratorService.findByUsername(principal.getName());
        if(administrator != null){
            return new ResponseEntity<>(new UserInfoDTO(administrator), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR, KUPAC, PRODAVAC')")
    @PostMapping("/korisnik")
    public ResponseEntity<Void> updateUser(@RequestBody UserInfoDTO userInfoDTO, Principal principal){

        Kupac kupac;
        Prodavac prodavac;
        Administrator administrator;

        kupac = kupacService.findByUsername(principal.getName());
        if(kupac != null){
            kupac.setUsername(userInfoDTO.getUsername());
            kupac.setAdresa(userInfoDTO.getAdresa());
            kupac.setPrezime(userInfoDTO.getPrezime());
            kupac.setIme(userInfoDTO.getIme());

            kupacService.save(kupac);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        prodavac = prodavacService.findByUsername(principal.getName());
        if(prodavac != null){
            prodavac.setUsername(userInfoDTO.getUsername());
            prodavac.setAdresa(userInfoDTO.getAdresa());
            prodavac.setPrezime(userInfoDTO.getPrezime());
            prodavac.setIme(userInfoDTO.getIme());

            prodavacService.save(prodavac);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        administrator = administratorService.findByUsername(principal.getName());
        if(administrator != null){
            administrator.setUsername(userInfoDTO.getUsername());
            administrator.setPrezime(userInfoDTO.getPrezime());
            administrator.setIme(userInfoDTO.getIme());

            administratorService.save(administrator);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
