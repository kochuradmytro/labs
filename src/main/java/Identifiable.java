import java.util.UUID;

/**
 * Інтерфейс для отримання UUID об'єкта.
 */
public interface Identifiable {
    /**
     * Повертає UUID об'єкта.
     */
    UUID getUuid();
}