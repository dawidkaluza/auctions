package eu.horyzont.auctions.web.data;

public class RequestResult {
    private final String message;
    private final Status status;

    public RequestResult(String message) {
        status = Status.INFORMATION;
        this.message = message;
    }

    public RequestResult(String message, Status status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    public enum Status {
        WARNING,
        ERROR,
        SUCCESS,
        INFORMATION,
        ;
    }
}
