package eu.horyzont.auctions.core;

public class EntityDoesntExistException extends Exception {
    public EntityDoesntExistException(Class<?> type, Long id) {
        super(
            String.format("Entity with type=%s and id=%d doesnt exist", type.getSimpleName(), id)
        );
    }
}
