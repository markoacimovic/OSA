package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ftn.osa.model.dto.RegistracijaDTO;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.model.enums.TipKorisnika;
import rs.ftn.osa.service.implementation.KupacService;
import rs.ftn.osa.service.implementation.ProdavacService;

import java.time.LocalDate;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private KupacService kupacService;

    @Autowired
    private ProdavacService prodavacService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> registration(@RequestBody RegistracijaDTO registracijaDTO) {

        if (registracijaDTO.getTipKorisnika().equals(TipKorisnika.KUPAC)) {
            if (kupacService.findByUsername(registracijaDTO.getUsername()) == null) {
                Kupac kupac = new Kupac(
                        registracijaDTO.getIme(),
                        registracijaDTO.getPrezime(),
                        registracijaDTO.getUsername(),
                        passwordEncoder.encode(registracijaDTO.getPassword()),
                        false,
                        registracijaDTO.getAdresa()
                );
                kupacService.save(kupac);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        if (prodavacService.findByUsername(registracijaDTO.getUsername()) == null) {
            Prodavac prodavac = new Prodavac(
                    registracijaDTO.getIme(),
                    registracijaDTO.getPrezime(),
                    registracijaDTO.getUsername(),
                    passwordEncoder.encode(registracijaDTO.getPassword()),
                    false,
                    LocalDate.now(),
                    registracijaDTO.getEmail(),
                    registracijaDTO.getAdresa()
            );
            prodavacService.save(prodavac);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}
