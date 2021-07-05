package rs.ftn.osa.model.entity;

import rs.ftn.osa.dto.PorudzbinaDTO;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "porudzbine")
public class Porudzbina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_porudzbina", unique = true, nullable = false)
    private Long id;

    @Column(name = "satnica", unique = false, nullable = false)
    private Date satnica;

    @Column(name = "dostavljeno", unique = false, nullable = false)
    private boolean dostavljeno;

    @Column(name = "ocena", unique = false, nullable = true)
    private int ocena;

    @Column(name = "komentar", unique = false, nullable = true)
    private String komentar;

    @Column(name = "anonimni_komentar", unique = false, nullable = true)
    private boolean anonimniKomentar;

    @Column(name = "arhivirani_komentar", unique = false, nullable = true)
    private boolean arhiviraniKomentar;

    @ManyToOne
    @JoinColumn(name = "kupac", referencedColumnName = "id_korisnik", nullable = true)
    private Kupac kupac;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "porudzbina")
    private Set<Stavka> stavke;

    public Porudzbina() {

    }

    public Porudzbina(Date satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimniKomentar, boolean arhiviraniKomentar, Kupac kupac, Set<Stavka> stavke) {
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimniKomentar = anonimniKomentar;
        this.arhiviraniKomentar = arhiviraniKomentar;
        this.kupac = kupac;
        this.stavke = stavke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public boolean isArhiviraniKomentar() {
        return arhiviraniKomentar;
    }

    public void setArhiviraniKomentar(boolean arhiviraniKomentar) {
        this.arhiviraniKomentar = arhiviraniKomentar;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Set<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(Set<Stavka> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Porudzbina{" +
                "satnica=" + satnica +
                ", dostavljeno=" + dostavljeno +
                ", ocena=" + ocena +
                ", komentar='" + komentar + '\'' +
                ", anonimniKomentar=" + anonimniKomentar +
                ", arhiviraniKomentar=" + arhiviraniKomentar +
                ", kupac=" + kupac +
                ", stavke=" + stavke +
                '}';
    }
}
