package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Stavka;

public class StavkaDTO {

    private String id;
    private int kolicina;
    private String porudzbinaId;
    private String artikalId;


    public StavkaDTO(Stavka stavka) {
        this.id = stavka.getId();
        this.kolicina = stavka.getKolicina();
        this.porudzbinaId = stavka.getPorudzbina();
        this.artikalId = stavka.getArtikal();
    }

    public StavkaDTO(String id, int kolicina, String porudzbinaId, String artikalId) {
        this.id = id;
        this.kolicina = kolicina;
        this.porudzbinaId = porudzbinaId;
        this.artikalId = artikalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbinaId(String porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public String getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(String artikalId) {
        this.artikalId = artikalId;
    }
}
