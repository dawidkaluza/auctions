package eu.horyzont.auctions.web.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 32)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
