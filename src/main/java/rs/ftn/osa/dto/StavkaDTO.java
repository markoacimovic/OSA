package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Stavka;

public class StavkaDTO {

    private Long id;
    private int kolicina;
    private long porudzbinaId;
    private long artikalId;

    public StavkaDTO(Long id, int kolicina, long porudzbinaId, long artikalId) {
        this.id = id;
        this.kolicina = kolicina;
        this.porudzbinaId = porudzbinaId;
        this.artikalId = artikalId;
    }

    public StavkaDTO(Stavka stavka) {
        this.id = stavka.getId();
        this.kolicina = stavka.getKolicina();
        this.porudzbinaId = stavka.getPorudzbina().getId();
        this.artikalId = stavka.getArtikal().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public long getPorudzbinaId() {
        return porudzbinaId;
    }

    public void setPorudzbina(long porudzbinaId) {
        this.porudzbinaId = porudzbinaId;
    }

    public long getArtikalId() {
        return artikalId;
    }

    public void setArtikal(long artikalId) {
        this.artikalId = artikalId;
    }
}
