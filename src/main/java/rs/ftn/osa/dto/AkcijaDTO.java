package rs.ftn.osa.dto;

import rs.ftn.osa.model.entity.Akcija;

import java.util.Date;

public class AkcijaDTO {

    private Long id;
    private int procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;
    private long prodavacId;

    public AkcijaDTO(Long id, int procenat, Date odKad, Date doKad, String tekst, long prodavacId) {
        this.id = id;
        this.procenat = procenat;
        this.odKad = odKad;
        this.doKad = doKad;
        this.tekst = tekst;
        this.prodavacId = prodavacId;
    }

    public AkcijaDTO(Akcija akcija) {
        this.id = akcija.getId();
        this.procenat = akcija.getProcenat();
        this.odKad = akcija.getOdKad();
        this.doKad = akcija.getDoKad();
        this.tekst = akcija.getTekst();
        this.prodavacId = akcija.getProdavac().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getProcenat() {
        return procenat;
    }

    public void setProcenat(int procenat) {
        this.procenat = procenat;
    }

    public Date getOdKad() {
        return odKad;
    }

    public void setOdKad(Date odKad) {
        this.odKad = odKad;
    }

    public Date getDoKad() {
        return doKad;
    }

    public void setDoKad(Date doKad) {
        this.doKad = doKad;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public long getProdavacId() {
        return prodavacId;
    }

    public void setProdavac(long prodavacId) {
        this.prodavacId = prodavacId;
    }
}
