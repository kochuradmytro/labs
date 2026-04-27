/**
 * Власний виняток для ситуацій, коли об'єкт одягу не знайдено.
 */
public class ClothesNotFoundException extends RuntimeException {

    /**
     * Створює виняток з повідомленням.
     */
    public ClothesNotFoundException(String message) {
        super(message);
    }
}