package rs.ftn.osa.dto;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ArtikalBackendDTO {

    private String naziv;
    private String opis;
    private Double cena;
   // private MultipartFile[] files;

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

//    public MultipartFile[] getFiles() {
//        return files;
//    }
//
//    public void setFiles(MultipartFile[] files) {
//        this.files = files;
//    }
}
