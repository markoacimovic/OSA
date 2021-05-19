package rs.ftn.osa.model.dto;

import rs.ftn.osa.model.entity.Artikal;

public class ArtikalDTO {

    private Long id;
    private String naziv;
    private String opis;
    private Double cena;
    private String putanjaSlike;
    private ProdavacDTO prodavac;

    public ArtikalDTO(Long id, String naziv, String opis, Double cena, String putanjaSlike, ProdavacDTO prodavac) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac = prodavac;
    }

    public ArtikalDTO(Artikal artikal) {
        this.id = artikal.getId();
        this.naziv = artikal.getNaziv();
        this.opis = artikal.getOpis();
        this.cena = artikal.getCena();
        this.putanjaSlike = artikal.getPutanjaSlike();
        this.prodavac = new ProdavacDTO(artikal.getProdavac());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ProdavacDTO getProdavac() {
        return prodavac;
    }

    public void setProdavac(ProdavacDTO prodavac) {
        this.prodavac = prodavac;
    }
}
