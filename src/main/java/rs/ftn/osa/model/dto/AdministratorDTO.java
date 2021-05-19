package rs.ftn.osa.model.dto;

import rs.ftn.osa.model.entity.Administrator;

public class AdministratorDTO {

    private Long id;
    private String ime;
    private String prezime;
    private String username;


    public AdministratorDTO(Long id, String ime, String prezime, String username) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
    }

    public AdministratorDTO(Administrator administrator) {
        this.id = administrator.getId();
        this.ime = administrator.getIme();
        this.prezime = administrator.getPrezime();
        this.username = administrator.getUsername();
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
}
