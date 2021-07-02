package rs.ftn.osa.dto;

public class BlockUserDTO {

    private String username;
    private boolean blokiran;

    public BlockUserDTO() {
    }

    public BlockUserDTO(String username, boolean blokiran) {
        this.username = username;
        this.blokiran = blokiran;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isBlokiran() {
        return blokiran;
    }

    public void setBlokiran(boolean blokiran) {
        this.blokiran = blokiran;
    }
}
