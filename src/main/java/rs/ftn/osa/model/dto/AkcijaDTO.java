package rs.ftn.osa.model.dto;

import rs.ftn.osa.model.entity.Akcija;

import java.util.Date;

public class AkcijaDTO {

    private Long id;
    private int procenat;
    private Date odKad;
    private Date doKad;
    private String tekst;
    private ProdavacDTO prodavac;

    public AkcijaDTO(Long id, int procenat, Date odKad, Date doKad, String tekst, ProdavacDTO prodavac) {
        this.id = id;
        this.procenat = procenat;
        this.odKad = odKad;
        this.doKad = doKad;
        this.tekst = tekst;
        this.prodavac = prodavac;
    }

    public AkcijaDTO(Akcija akcija) {
        this.id = akcija.getId();
        this.procenat = akcija.getProcenat();
        this.odKad = akcija.getOdKad();
        this.doKad = akcija.getDoKad();
        this.tekst = akcija.getTekst();
        this.prodavac = new ProdavacDTO(akcija.getProdavac());
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

    public ProdavacDTO getProdavac() {
        return prodavac;
    }

    public void setProdavac(ProdavacDTO prodavac) {
        this.prodavac = prodavac;
    }
}
