package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Kupac;

public class KupacDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String username;
    private String adresa;

    public KupacDTO(Long id, String ime, String prezime, String username, String adresa) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.adresa = adresa;
    }

    public KupacDTO(Kupac kupac) {
        this.id = kupac.getId();
        this.ime = kupac.getIme();
        this.prezime = kupac.getPrezime();
        this.username = kupac.getUsername();
        this.adresa = kupac.getAdresa();
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
}
