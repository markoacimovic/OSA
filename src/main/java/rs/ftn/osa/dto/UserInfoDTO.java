package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Administrator;
import rs.ftn.osa.model.entity.Kupac;
import rs.ftn.osa.model.entity.Prodavac;

public class UserInfoDTO {

    private String ime;
    private String prezime;
    private String username;
    private String adresa;

    public UserInfoDTO() {
    }

    public UserInfoDTO(Kupac kupac){
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.username = kupac.getUsername();
        this.adresa = kupac.getAdresa();
    }

    public UserInfoDTO(Prodavac prodavac){
        this.ime = prodavac.getIme();
        this.prezime = prodavac.getPrezime();
        this.username = prodavac.getUsername();
        this.adresa = prodavac.getAdresa();
    }

    public UserInfoDTO(Administrator administrator){
        this.ime = administrator.getIme();
        this.prezime = administrator.getPrezime();
        this.username = administrator.getUsername();
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
}
