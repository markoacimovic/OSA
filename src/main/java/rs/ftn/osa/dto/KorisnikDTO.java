package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Korisnik;

public class KorisnikDTO {

    private String username;
    private String ime;
    private String prezime;
    private boolean blokiran;

    public KorisnikDTO() {
    }

    public KorisnikDTO(String username, String ime, String prezime) {
        this.username = username;
        this.ime = ime;
        this.prezime = prezime;
    }

    public KorisnikDTO(Korisnik korisnik){
        this.ime = korisnik.getIme();
        this.prezime = korisnik.getPrezime();
        this.username = korisnik.getUsername();
        this.blokiran = korisnik.isBlokiran();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
}
