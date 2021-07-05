package rs.ftn.osa.model.entity;

import org.springframework.security.core.GrantedAuthority;
import rs.ftn.osa.dto.AdministratorDTO;
import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "administrator")
public class Administrator extends Korisnik {

    public Administrator() {
        super();
    }

    public Administrator(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
    }

}
