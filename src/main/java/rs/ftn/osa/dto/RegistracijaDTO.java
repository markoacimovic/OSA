package rs.ftn.osa.dto;

import rs.ftn.osa.model.enums.TipKorisnika;

public class RegistracijaDTO {

    private String ime;
    private String prezime;
    private String username;
    private String password;
    private String adresa;
    private String email;
    private TipKorisnika tipKorisnika;

    public RegistracijaDTO(String ime, String prezime, String username, String password, String adresa, String email, TipKorisnika tipKorisnika) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.adresa = adresa;
        this.email = email;
        this.tipKorisnika = tipKorisnika;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }
}
