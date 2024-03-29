package rs.ftn.osa.model.entity;

import rs.ftn.osa.dto.AkcijaDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "akcije")
public class Akcija {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_akcija", unique = true, nullable = false)
    private Long id;

    @Column(name = "procenat", unique = false, nullable = false)
    private int procenat;

    @Column(name = "od_kad", unique = false, nullable = false)
    private Date odKad;

    @Column(name = "do_kad", unique = false, nullable = false)
    private Date doKad;

    @Column(name = "tekst", unique = false, nullable = false)
    private String tekst;

    @ManyToOne
    @JoinColumn(name = "prodavac", referencedColumnName = "id_korisnik", nullable = false)
    private Prodavac prodavac;


    public Akcija() {
    }

    public Akcija(int procenat, Date odKad, Date doKad, String tekst, Prodavac prodavac) {
        this.procenat = procenat;
        this.odKad = odKad;
        this.doKad = doKad;
        this.tekst = tekst;
        this.prodavac = prodavac;
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

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    @Override
    public String toString() {
        return "Akcija{" +
                "procenat=" + procenat +
                ", odKad=" + odKad +
                ", doKad=" + doKad +
                ", tekst='" + tekst + '\'' +
                ", prodavac=" + prodavac +
                '}';
    }
}
