package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Artikal;

public class ArtikalDTO {

    private String id;
    private String naziv;
    private String opis;
    private Double cena;
    private String putanjaSlike;
    private Double ocena;
    private Integer brojKomentara;

    public ArtikalDTO(String id, String naziv, String opis, Double cena, String putanjaSlike) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
    }

    public ArtikalDTO(Artikal artikal) {
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
        this.putanjaSlike = artikal.getFilename();
        this.brojKomentara = artikal.getBrojKomentara();
        this.ocena = artikal.getOcena();
    }

    public ArtikalDTO(String id, String naziv, String opis, Double cena, String putanjaSlike, Double ocena, Integer brojKomentara) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.ocena = ocena;
        this.brojKomentara = brojKomentara;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPutanjaSlike() {
        return putanjaSlike;
    }

    public void setPutanjaSlike(String putanjaSlike) {
        this.putanjaSlike = putanjaSlike;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public Integer getBrojKomentara() {
        return brojKomentara;
    }

    public void setBrojKomentara(Integer brojKomentara) {
        this.brojKomentara = brojKomentara;
    }
}
