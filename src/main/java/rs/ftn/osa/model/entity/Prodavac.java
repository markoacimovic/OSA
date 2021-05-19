package rs.ftn.osa.model.entity;

import rs.ftn.osa.model.dto.ProdavacDTO;
import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "prodavci")
public class Prodavac extends Korisnik {

    @Column(name = "posluje_od", unique = false, nullable = false)
    private LocalDate poslujeOd;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "adresa", unique = false, nullable = false)
    private String adresa;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prodavac")
    private Set<Akcija> akcije;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "prodavac")
    private Set<Artikal> artikli;

    public Prodavac() {
        super();
    }

    public Prodavac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, LocalDate poslujeOd, String email, String adresa) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.poslujeOd = poslujeOd;
        this.email = email;
        this.adresa = adresa;
    }

    public Prodavac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, LocalDate poslujeOd, String email, String adresa, Set<Artikal> artikli) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.poslujeOd = poslujeOd;
        this.email = email;
        this.adresa = adresa;
        this.artikli = artikli;
    }

    public Prodavac(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika, LocalDate poslujeOd, String email, String adresa, Set<Akcija> akcije, Set<Artikal> artikli) {
        super(ime, prezime, username, password, blokiran, tipKorisnika);
        this.poslujeOd = poslujeOd;
        this.email = email;
        this.adresa = adresa;
        this.akcije = akcije;
        this.artikli = artikli;
    }

    public Prodavac(ProdavacDTO prodavacDTO) {
        this.id = prodavacDTO.getId();
        this.ime = prodavacDTO.getIme();
        this.prezime = prodavacDTO.getPrezime();
        this.username = prodavacDTO.getUsername();
        this.adresa = prodavacDTO.getAdresa();
        this.poslujeOd = prodavacDTO.getPoslujeOd();
        this.email = prodavacDTO.getEmail();
    }

    public LocalDate getPoslujeOd() {
        return poslujeOd;
    }

    public void setPoslujeOd(LocalDate poslujeOd) {
        this.poslujeOd = poslujeOd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Set<Akcija> getAkcije() {
        return akcije;
    }

    public void setAkcije(Set<Akcija> akcije) {
        this.akcije = akcije;
    }

    public Set<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(Set<Artikal> artikli) {
        this.artikli = artikli;
    }

    @Override
    public String toString() {
        return "Prodavac{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", blokiran=" + blokiran +
                ", poslujeOd=" + poslujeOd +
                ", email='" + email + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}