import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Клас тестів для Store.
 */
public class StoreTest {

    /**
     * Перевіряє, що delete кидає виняток, якщо об'єкт не знайдено.
     */
    @Test
    void shouldThrowClothesNotFoundExceptionWhenDeletingNonExistingObject() {
        Store store = new Store("Test Store");
        Clothes clothes = new Pants("Jeans", "M", 1200.0, "Denim");

        assertThrows(ClothesNotFoundException.class, () -> {
            store.delete(clothes);
        });
    }

    /**
     * Перевіряє, що update кидає виняток, якщо об'єкт не знайдено.
     */
    @Test
    void shouldThrowClothesNotFoundExceptionWhenUpdatingNonExistingObject() {
        Store store = new Store("Test Store");
        Clothes existingObject = new Shirts("Office Shirt", "L", 900.0, "Long");
        Clothes newObject = new Shirts("New Office Shirt", "L", 950.0, "Long");

        assertThrows(ClothesNotFoundException.class, () -> {
            store.update(existingObject, newObject);
        });
    }

    /**
     * Перевіряє, що конструктор Clothes кидає виняток при некоректній ціні.
     */
    @Test
    void shouldThrowInvalidFieldValueExceptionWhenCreatingObjectWithInvalidPrice() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new Pants("Jeans", "M", -100.0, "Denim");
        });
    }
}