package rs.ftn.osa.dto;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ArtikalBackendDTO {

    private String naziv;
    private String opis;
    private Double cena;
    private CommonsMultipartFile slika;

    public ArtikalBackendDTO() {
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public CommonsMultipartFile getSlika() {
        return slika;
    }

    public void setSlika(CommonsMultipartFile slika) {
        this.slika = slika;
    }
}
