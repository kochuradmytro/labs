/**
 * Власний виняток для некоректних значень полів.
 */
public class InvalidFieldValueException extends IllegalArgumentException {

    /**
     * Створює виняток з повідомленням.
     */
    public InvalidFieldValueException(String message) {
        super(message);
    }
}