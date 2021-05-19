package rs.ftn.osa.model.entity;

import rs.ftn.osa.model.dto.KupacDTO;
import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "kupac")
public class Kupac extends Korisnik {

    @Column(name = "adresa", unique = false, nullable = false)
    private String adresa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "kupac")
    private Set<Porudzbina> porudzbine;

    public Kupac() {
        super();
    }

    public Kupac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, String adresa, Set<Porudzbina> porudzbine) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.adresa = adresa;
        this.porudzbine = porudzbine;
    }

    public Kupac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, String adresa) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.adresa = adresa;
    }

    public Kupac(KupacDTO kupacDTO) {
        this.id = kupacDTO.getId();
        this.ime = kupacDTO.getIme();
        this.prezime = kupacDTO.getPrezime();
        this.username = kupacDTO.getUsername();
        this.adresa = kupacDTO.getAdresa();
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(Set<Porudzbina> porudzbine) {
        this.porudzbine = porudzbine;
    }

    @Override
    public String toString() {
        return "Kupac{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", blokiran=" + blokiran +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
