package eu.horyzont.auctions.modules.bid;

public class NewBidMustBeGreaterException extends Exception {
    public NewBidMustBeGreaterException() {
        super("New bid must bu higher than previous");
    }
}
