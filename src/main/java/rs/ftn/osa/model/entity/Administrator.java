package rs.ftn.osa.model.entity;

import rs.ftn.osa.model.dto.AdministratorDTO;
import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator extends Korisnik {

    public Administrator() {
        super();
    }

    public Administrator(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
    }

    public Administrator(AdministratorDTO administratorDTO) {

        this.id = administratorDTO.getId();
        this.ime = administratorDTO.getIme();
        this.prezime = administratorDTO.getPrezime();
        this.username = administratorDTO.getUsername();
    }
}
