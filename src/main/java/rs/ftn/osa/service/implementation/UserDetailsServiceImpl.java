package rs.ftn.osa.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ftn.osa.model.entity.Korisnik;
import rs.ftn.osa.service.interfaces.IAdministratorService;
import rs.ftn.osa.service.interfaces.IKupacService;
import rs.ftn.osa.service.interfaces.IProdavacService;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IKupacService kupacService;

    @Autowired
    private IProdavacService prodavacService;

    @Autowired
    private IAdministratorService administratorService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Korisnik korisnik = kupacService.findByUsername(username);

        if (korisnik == null) {
            korisnik = prodavacService.findByUsername(username);
            if (korisnik == null) {
                korisnik = administratorService.findByUsername(username);
                if (korisnik == null) {
                    throw new UsernameNotFoundException("No user with that username");
                }
            }
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String role = "ROLE_" + korisnik.getUserRole().toString();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return new User(
                korisnik.getUsername().trim(),
                korisnik.getPassword().trim(),
                grantedAuthorities
        );
    }
}
