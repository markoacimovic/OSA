package rs.ftn.osa.dto;

public class NarudzbinaDTO {

    private long artikalId;
    private String kolicina;

    public NarudzbinaDTO() {
    }

    public long getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(long artikalId) {
        this.artikalId = artikalId;
    }

    public String getKolicina() {
        return kolicina;
    }

    public void setKolicina(String kolicina) {
        this.kolicina = kolicina;
    }
}
