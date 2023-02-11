package rs.ftn.osa.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import rs.ftn.osa.dto.PorudzbinaDTO;

import java.util.Date;
import java.util.Set;

@Document(indexName = "porudzbine")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Porudzbina {

    @Id
    private String id;

    @Field(type = FieldType.Date)
    private Date satnica;

    @Field(type = FieldType.Boolean)
    private boolean dostavljeno;

    @Field(type = FieldType.Integer)
    private int ocena;

    @Field(type = FieldType.Text)
    private String komentar;

    @Field(type = FieldType.Boolean)
    private boolean anonimniKomentar;

    @Field(type = FieldType.Boolean)
    private boolean arhiviraniKomentar;

    @Field(type = FieldType.Keyword)
    private String kupac;

    @Field(type = FieldType.Nested)
    private Set<Stavka> stavke;

    public Porudzbina() {

    }

    public Porudzbina(Date satnica, boolean dostavljeno, int ocena, String komentar, boolean anonimniKomentar, boolean arhiviraniKomentar, String kupac, Set<Stavka> stavke) {
        this.satnica = satnica;
        this.dostavljeno = dostavljeno;
        this.ocena = ocena;
        this.komentar = komentar;
        this.anonimniKomentar = anonimniKomentar;
        this.arhiviraniKomentar = arhiviraniKomentar;
        this.kupac = kupac;
        this.stavke = stavke;
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

    public boolean isArhiviraniKomentar() {
        return arhiviraniKomentar;
    }

    public void setArhiviraniKomentar(boolean arhiviraniKomentar) {
        this.arhiviraniKomentar = arhiviraniKomentar;
    }

    public String getKupac() {
        return kupac;
    }

    public void setKupac(String kupac) {
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
