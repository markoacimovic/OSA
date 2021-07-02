package rs.ftn.osa.dto;

public class KomentariDTO {

    private String username;
    private String komentar;
    private boolean anonimno;

    public KomentariDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public boolean isAnonimno() {
        return anonimno;
    }

    public void setAnonimno(boolean anonimno) {
        this.anonimno = anonimno;
    }
}
