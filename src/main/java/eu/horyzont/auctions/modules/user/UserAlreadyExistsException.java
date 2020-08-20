package eu.horyzont.auctions.modules.user;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String email) {
        super("User with given email already exists: " + email);
    }
}
