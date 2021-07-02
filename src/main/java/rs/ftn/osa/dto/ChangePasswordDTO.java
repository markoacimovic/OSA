package rs.ftn.osa.dto;

public class ChangePasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String newRepeatedPassword;

    public ChangePasswordDTO() {
    }

    public ChangePasswordDTO(String currentPassword, String newPassword, String newRepeatedPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newRepeatedPassword = newRepeatedPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewRepeatedPassword() {
        return newRepeatedPassword;
    }

    public void setNewRepeatedPassword(String newRepeatedPassword) {
        this.newRepeatedPassword = newRepeatedPassword;
    }
}
