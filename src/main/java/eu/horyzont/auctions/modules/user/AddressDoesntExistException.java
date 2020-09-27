package eu.horyzont.auctions.modules.user;

public class AddressDoesntExistException extends Exception {
    public AddressDoesntExistException(Long id) {
        super(
            String.format("Address with id=%d doesnt exist", id)
        );
    }
}
