package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Porudzbina;

import java.util.Date;

public class PorudzbinaDTO {

    private String id;
    private Date satnica;
    private boolean dostavljeno;
    private int ocena;
    private String komentar;
    private boolean anonimniKomentar;
    private String prodavac;

    public PorudzbinaDTO() {
    }

    public PorudzbinaDTO(String id, Date satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimniKomentar) {
        this.id = id;
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimniKomentar = anonimniKomentar;
    }

    public PorudzbinaDTO(Porudzbina porudzbina) {
        this.id = porudzbina.getId();
        this.satnica = porudzbina.getSatnica();
        this.dostavljeno = porudzbina.isDostavljeno();
        this.ocena = porudzbina.getOcena();
        this.komentar = porudzbina.getKomentar();
        this.anonimniKomentar = porudzbina.isAnonimniKomentar();
    }

    public String getProdavac() {
        return prodavac;
    }

    public void setProdavac(String prodavac) {
        this.prodavac = prodavac;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getSatnica() {
        return satnica;
    }

    public void setSatnica(Date satnica) {
        this.satnica = satnica;
    }

    public boolean isDostavljeno() {
        return dostavljeno;
    }

    public void setDostavljeno(boolean dostavljeno) {
        this.dostavljeno = dostavljeno;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public boolean isAnonimniKomentar() {
        return anonimniKomentar;
    }

    public void setAnonimniKomentar(boolean anonimniKomentar) {
        this.anonimniKomentar = anonimniKomentar;
    }

}
