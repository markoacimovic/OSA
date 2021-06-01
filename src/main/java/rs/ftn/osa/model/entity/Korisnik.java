package rs.ftn.osa.model.entity;

import org.springframework.security.core.userdetails.UserDetails;
import rs.ftn.osa.model.enums.UserRole;

import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Korisnik{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_korisnik", unique = true, nullable = false)
    protected Long id;

    @Column(name = "ime", unique = false, nullable = false)
    protected String ime;

    @Column(name = "prezime", unique = false, nullable = false)
    protected String prezime;

    @Column(name = "username", unique = true, nullable = false)
    protected String username;

    @Column(name = "password", unique = false, nullable = false)
    protected String password;

    @Column(name = "blokiran", unique = false, nullable = false)
    protected boolean blokiran;

    @Column(name = "tip_korisnika", unique = false, nullable = false)
    @Enumerated(EnumType.STRING)
    protected UserRole userRole;

    public Korisnik() {

    }

    public Korisnik(String ime, String prezime, String username, String password, boolean blokiran, UserRole tipKorisnika) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.password = password;
        this.blokiran = blokiran;
        this.userRole = tipKorisnika;
    }

    public Korisnik(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }


    @Override
    public String toString() {
        return "Korisnik{" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", blokiran=" + blokiran +
                '}';
    }
}
