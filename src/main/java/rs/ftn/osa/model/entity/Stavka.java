package rs.ftn.osa.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import rs.ftn.osa.dto.StavkaDTO;


@Document(indexName = "stavke")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Stavka {

    @Id
    private String id;

    @Field(type = FieldType.Integer)
    private int kolicina;

    @Field(type = FieldType.Keyword)
    private String porudzbina;

    @Field(type = FieldType.Keyword)
    private String artikal;

    public Stavka() {
    }

    public Stavka(String id, int kolicina, String porudzbina, String artikal) {
        this.id = id;
        this.kolicina = kolicina;
        this.porudzbina = porudzbina;
        this.artikal = artikal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public String getPorudzbina() {
        return porudzbina;
    }

    public void setPorudzbina(String porudzbina) {
        this.porudzbina = porudzbina;
    }

    public String getArtikal() {
        return artikal;
    }

    public void setArtikal(String artikal) {
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
