package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Prodavac;

import java.time.LocalDate;

public class ProdavacDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String username;
    private String adresa;
    private LocalDate poslujeOd;
    private String email;

    public ProdavacDTO(Long id, String ime, String prezime, String username, String adresa, LocalDate poslujeOd, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.adresa = adresa;
        this.poslujeOd = poslujeOd;
        this.email = email;
    }

    public ProdavacDTO(Prodavac prodavac) {
        this.id = prodavac.getId();
        this.ime = prodavac.getIme();
        this.prezime = prodavac.getPrezime();
        this.username = prodavac.getUsername();
        this.adresa = prodavac.getAdresa();
        this.poslujeOd = prodavac.getPoslujeOd();
        this.email = prodavac.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
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
}
