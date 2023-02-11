package rs.ftn.osa.model.entity;

import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.*;

@Entity
@Table(name = "kupac")
public class Kupac extends Korisnik {

    @Column(name = "adresa", unique = false, nullable = false)
    private String adresa;

    @Column(name = "porudzbine")
    private String porudzbine;

    public Kupac() {
        super();
    }

    public Kupac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, String adresa, String porudzbine) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.adresa = adresa;
        this.porudzbine = porudzbine;
    }

    public Kupac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, String adresa) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getPorudzbine() {
        return porudzbine;
    }

    public void setPorudzbine(String porudzbine) {
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
