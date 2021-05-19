package rs.ftn.osa.model.dto;

import rs.ftn.osa.model.entity.Stavka;

public class StavkaDTO {

    private Long id;
    private int kolicina;
    private PorudzbinaDTO porudzbina;
    private ArtikalDTO artikal;

    public StavkaDTO(Long id, int kolicina, PorudzbinaDTO porudzbina, ArtikalDTO artikal) {
        this.id = id;
        this.kolicina = kolicina;
        this.porudzbina = porudzbina;
        this.artikal = artikal;
    }

    public StavkaDTO(Stavka stavka) {
        this.id = stavka.getId();
        this.kolicina = stavka.getKolicina();
        this.porudzbina = new PorudzbinaDTO(stavka.getPorudzbina());
        this.artikal = new ArtikalDTO(stavka.getArtikal());
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

    public PorudzbinaDTO getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(PorudzbinaDTO porudzbina) {
        this.porudzbina = porudzbina;
    }

    public ArtikalDTO getArtikal() {
        return artikal;
    }

    public void setArtikal(ArtikalDTO artikal) {
        this.artikal = artikal;
    }
}
