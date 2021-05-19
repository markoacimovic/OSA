package rs.ftn.osa.model.entity;

import rs.ftn.osa.model.dto.ArtikalDTO;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "artikli")
public class Artikal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artikal", unique = true, nullable = false)
    private Long id;

    @Column(name = "naziv", unique = false, nullable = false)
    private String naziv;

    @Column(name = "opis", unique = false, nullable = false)
    private String opis;

    @Column(name = "cena", unique = false, nullable = false)
    private Double cena;

    @Column(name = "putanja_slike", unique = false, nullable = false)
    private String putanjaSlike;

    @ManyToOne
    @JoinColumn(name = "prodavac", referencedColumnName = "id_korisnik", nullable = false)
    private Prodavac prodavac;

    @ManyToMany
    @JoinColumn(name = "akcija", referencedColumnName = "id_akcija", nullable = false)
    private Set<Akcija> akcije;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "artikal")
    private Set<Stavka> stavke;

    public Artikal() {

    }

    public Artikal(String naziv, String opis, Double cena, String putanjaSlike, Prodavac prodavac) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac = prodavac;
    }

    public Artikal(String naziv, String opis, Double cena, String putanjaSlike, Prodavac prodavac, Set<Akcija> akcije) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac = prodavac;
        this.akcije = akcije;
    }

    public Artikal(String naziv, String opis, Double cena, String putanjaSlike, Prodavac prodavac, Set<Akcija> akcije, Set<Stavka> stavke) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.putanjaSlike = putanjaSlike;
        this.prodavac = prodavac;
        this.akcije = akcije;
        this.stavke = stavke;
    }

    public Artikal(ArtikalDTO artikalDTO) {
        this.id = artikalDTO.getId();
        this.naziv = artikalDTO.getNaziv();
        this.opis = artikalDTO.getOpis();
        this.cena = artikalDTO.getCena();
        this.putanjaSlike = artikalDTO.getPutanjaSlike();
        ;
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

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    public Set<Akcija> getAkcije() {
        return akcije;
    }

    public void setAkcije(Set<Akcija> akcije) {
        this.akcije = akcije;
    }

    public Set<Stavka> getStavke() {
        return stavke;
    }

    public void setStavke(Set<Stavka> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String toString() {
        return "Artikal{" +
                "naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                ", putanjaSlike='" + putanjaSlike + '\'' +
                ", prodavac=" + prodavac +
                ", akcije=" + akcije +
                '}';
    }
}
