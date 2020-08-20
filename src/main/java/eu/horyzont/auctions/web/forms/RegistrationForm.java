package eu.horyzont.auctions.web.forms;

import eu.horyzont.auctions.web.constraints.PasswordMatches;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@PasswordMatches
public class RegistrationForm implements Serializable {
    private static final long serialVersionUID = -8582553475226281591L;

    @NotNull
    @Size(min = 3, max = 32)
    private String firstName;

    @NotNull
    @Size(min = 3, max = 32)
    private String lastName;

    @NotNull
    @Size(min = 3, max = 64)
    @Email
    private String email;

    @NotNull
    @Size(min = 3, max = 32)
    private String password;

    @NotNull
    @Size(min = 3, max = 32)
    private String repeatedPassword;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getRepeatedPassword() {
        return repeatedPassword;
    }

    public void setRepeatedPassword(String repeatedPassword) {
        this.repeatedPassword = repeatedPassword;
    }
}
