package rs.ftn.osa.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Set;

@Document(indexName = "artikli")
@Setting(settingPath = "/analyzers/serbianAnalyzer.json")
public class Artikal {

    @Id
    private String id;

    @Field(type = FieldType.Keyword)
    private String naziv;

    @Field(type = FieldType.Text)
    private String opis;

    @Field(type = FieldType.Double)
    private Double cena;

    @Field(type = FieldType.Keyword)
    private String filename;

    @Field(type = FieldType.Keyword)
    private String prodavac;

    @Field(type = FieldType.Keyword)
    private String keywords;

    @Field(type = FieldType.Double)
    private Double ocena;

    @Field(type = FieldType.Integer)
    private Integer brojKomentara;

    @Field(type = FieldType.Nested)
    private Set<Stavka> stavke;

    public Artikal() {

    }

    public Artikal(String naziv, String opis, Double cena, String filename, String prodavac) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.filename = filename;
        this.prodavac = prodavac;
    }

    public Artikal(String naziv, String opis, Double cena, String filename, String prodavac, Set<Stavka> stavke) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.filename = filename;
        this.prodavac = prodavac;
        this.stavke = stavke;
    }

    public Artikal(String id, String naziv, String opis, Double cena, String filename, String prodavac, String keywords, Double ocena, Integer brojKomentara, Set<Stavka> stavke) {
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.filename = filename;
        this.prodavac = prodavac;
        this.keywords = keywords;
        this.ocena = ocena;
        this.brojKomentara = brojKomentara;
        this.stavke = stavke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Double getOcena() {
        return ocena;
    }

    public void setOcena(Double ocena) {
        this.ocena = ocena;
    }

    public Integer getBrojKomentara() {
        return brojKomentara;
    }

    public void setBrojKomentara(Integer brojKomentara) {
        this.brojKomentara = brojKomentara;
    }

    public String getProdavac() {
        return prodavac;
    }

    public void setProdavac(String prodavac) {
        this.prodavac = prodavac;
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
                ", putanjaSlike='" + filename + '\'' +
                ", prodavac=" + prodavac +
                '}';
    }
}
