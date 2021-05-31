package rs.ftn.osa.model.entity;

import rs.ftn.osa.dto.StavkaDTO;

import javax.persistence.*;

@Entity
@Table(name = "stavke")
public class Stavka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_stavka", unique = true, nullable = false)
    private Long id;

    @Column(name = "kolicina", unique = false, nullable = false)
    private int kolicina;

    @ManyToOne
    @JoinColumn(name = "porudzbina", referencedColumnName = "id_porudzbina", nullable = false)
    private Porudzbina porudzbina;

    @ManyToOne
    @JoinColumn(name = "artikal", referencedColumnName = "id_artikal", nullable = false)
    private Artikal artikal;

    public Stavka() {
    }

    public Stavka(int kolicina, Porudzbina porudzbina, Artikal artikal) {
        this.kolicina = kolicina;
        this.porudzbina = porudzbina;
        this.artikal = artikal;
    }

    public Stavka(StavkaDTO stavkaDTO) {
        this.id = stavkaDTO.getId();
        this.kolicina = stavkaDTO.getKolicina();
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

    public Porudzbina getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(Porudzbina porudzbina) {
        this.porudzbina = porudzbina;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    @Override
    public String toString() {
        return "Stavka{" +
                "kolicina=" + kolicina +
                ", porudzbina=" + porudzbina +
                ", artikal=" + artikal +
                '}';
    }
}
