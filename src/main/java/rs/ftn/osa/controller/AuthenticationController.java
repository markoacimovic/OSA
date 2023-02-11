package rs.ftn.osa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

import rs.ftn.osa.service.interfaces.IAdministratorService;
import rs.ftn.osa.service.interfaces.IKupacService;
import rs.ftn.osa.service.interfaces.IProdavacService;
import rs.ftn.osa.support.LoggerStatic;

import javax.annotation.security.PermitAll;
import java.security.Principal;
import java.time.LocalDate;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthenticationController {

    private static final String ROLE = "ROLE";

    private final IKupacService kupacService;
    private final IProdavacService prodavacService;
    private final IAdministratorService administratorService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final TokenUtils tokenUtils;
    private final AuthenticationManager authenticationManager;

    public AuthenticationController(IKupacService kupacService, IProdavacService prodavacService, IAdministratorService administratorService, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, TokenUtils tokenUtils, AuthenticationManager authenticationManager) {
        this.kupacService = kupacService;
        this.prodavacService = prodavacService;
        this.administratorService = administratorService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
    }

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

                LoggerStatic.logInFile(AuthenticationController.class, "Registrovao se " + registracijaDTO.getUsername());

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

            LoggerStatic.logInFile(AuthenticationController.class, "Registrovao se " + registracijaDTO.getUsername());

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

        LoggerStatic.logInFile(AuthenticationController.class, "Ulogovan je " + prijavaDTO.getUsername());

       try {
           UserDetails userDetails = userDetailsService.loadUserByUsername(prijavaDTO.getUsername());
           return ResponseEntity.ok(tokenUtils.generateToken(userDetails));
       }catch (UsernameNotFoundException e){
           return ResponseEntity.notFound().build();
       }
    }

    @PreAuthorize("hasAnyRole('ADMINISTRATOR, KUPAC, PRODAVAC')")
    @PostMapping(value = "/change-password", consumes = "application/json")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Principal principal){

        if(!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getNewRepeatedPassword())){
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
