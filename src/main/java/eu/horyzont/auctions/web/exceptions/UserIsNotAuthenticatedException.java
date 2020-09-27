package eu.horyzont.auctions.web.exceptions;

public class UserIsNotAuthenticatedException extends RuntimeException {
    public UserIsNotAuthenticatedException() {
        super("User is no authenticated");
    }
}
