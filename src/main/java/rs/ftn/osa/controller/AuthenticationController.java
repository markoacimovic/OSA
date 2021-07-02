package rs.ftn.osa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import rs.ftn.osa.dto.ChangePasswordDTO;
import rs.ftn.osa.model.entity.Korisnik;
import rs.ftn.osa.security.TokenUtils;
import rs.ftn.osa.dto.PrijavaDTO;
import rs.ftn.osa.dto.RegistracijaDTO;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Prodavac;
import rs.ftn.osa.model.enums.TipKorisnika;
import rs.ftn.osa.model.enums.UserRole;

import rs.ftn.osa.service.IAdministratorService;
import rs.ftn.osa.service.IKupacService;
import rs.ftn.osa.service.IProdavacService;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private static final String ROLE = "ROLE";

    @Autowired
    private IKupacService kupacService;

    @Autowired
    private IProdavacService prodavacService;

    @Autowired
    private IAdministratorService administratorService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PermitAll
    @PostMapping(value = "/registration", consumes = "application/json")
    public ResponseEntity<Void> registration(@RequestBody RegistracijaDTO registracijaDTO) {

        if (registracijaDTO.getTipKorisnika().equals(TipKorisnika.KUPAC)) {
            if (kupacService.findByUsername(registracijaDTO.getUsername()) == null) {
                Kupac kupac = new Kupac();
                kupac.setIme(registracijaDTO.getIme());
                kupac.setPrezime(registracijaDTO.getPrezime());
                kupac.setUsername(registracijaDTO.getUsername());
                kupac.setPassword(passwordEncoder.encode(registracijaDTO.getPassword()));
                kupac.setBlokiran(false);
                kupac.setAdresa(registracijaDTO.getAdresa());
                kupac.setUserRole(UserRole.KUPAC);

                kupacService.save(kupac);
                return new ResponseEntity<>(CREATED);
            }
            return new ResponseEntity<>(CONFLICT);
        }

        if (prodavacService.findByUsername(registracijaDTO.getUsername()) == null) {
            Prodavac prodavac = new Prodavac();
            prodavac.setIme(registracijaDTO.getIme());
            prodavac.setPrezime(registracijaDTO.getPrezime());
            prodavac.setUsername(registracijaDTO.getUsername());
            prodavac.setPassword(passwordEncoder.encode(registracijaDTO.getPassword()));
            prodavac.setBlokiran(false);
            prodavac.setPoslujeOd(LocalDate.now());
            prodavac.setEmail(registracijaDTO.getEmail());
            prodavac.setAdresa(registracijaDTO.getAdresa());
            prodavac.setNaziv(registracijaDTO.getNaziv());
            prodavac.setUserRole(UserRole.PRODAVAC);

            prodavacService.save(prodavac);
            return new ResponseEntity<>(CREATED);
        }
        return new ResponseEntity<>(CONFLICT);
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<String> login(@RequestBody PrijavaDTO prijavaDTO){

        Korisnik korisnik = prodavacService.findByUsername(prijavaDTO.getUsername());
        if(korisnik != null){
            if(korisnik.isBlokiran()){
                return new ResponseEntity<>(FORBIDDEN);
            }
        }
        korisnik = kupacService.findByUsername(prijavaDTO.getUsername());
        if(korisnik != null){
            if(korisnik.isBlokiran())
                return new ResponseEntity<>(FORBIDDEN);
        }


        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    prijavaDTO.getUsername(),
                    prijavaDTO.getPassword()
            )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

       try {
           UserDetails userDetails = userDetailsService.loadUserByUsername(prijavaDTO.getUsername());
           return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
       }catch (UsernameNotFoundException e){
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping(value = "/change-password", consumes = "application/json")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Principal principal){

        if(changePasswordDTO.getNewPassword() != changePasswordDTO.getNewRepeatedPassword()){
            return new ResponseEntity<>(BAD_REQUEST);
        }

        Kupac kupac;
        Prodavac prodavac;
        String password = passwordEncoder.encode(changePasswordDTO.getNewPassword());

        kupac = kupacService.findByUsername(principal.getName());
        if(kupac != null){
            kupac.setPassword(password);
            kupacService.save(kupac);
            return new ResponseEntity<>(OK);
        }
        prodavac = prodavacService.findByUsername(principal.getName());
        if(prodavac != null){
            prodavac.setPassword(password);
            prodavacService.save(prodavac);
            return new ResponseEntity<>(OK);
        }

        return new ResponseEntity<>(BAD_REQUEST);
    }
}
