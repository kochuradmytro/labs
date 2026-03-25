import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Клас для тестування перевірок у Clothes.
 */
class ClothesTest {

    /**
     * Перевіряє, що сетер кидає виняток при некоректній ціні.
     */
    @Test
    void shouldThrowExceptionWhenInvalidValueInSetter() {
        Clothes clothes = new Clothes(1, "Футболка", "M", 500.0);

        assertThrows(IllegalArgumentException.class, () -> {
            clothes.setPrice(-100.0);
        });
    }

    /**
     * Перевіряє, що конструктор кидає виняток при некоректних даних.
     */
    @Test
    void shouldThrowExceptionWhenInvalidConstructorData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Clothes(0, "", null, -50.0);
        });
    }
}