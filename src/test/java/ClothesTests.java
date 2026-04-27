import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Клас для тестування перевірок у Clothes.
 */
class ClothesTests {
    /**
     * Перевіряє, що сетер кидає власний виняток при некоректній ціні.
     */
    @Test
    void shouldThrowInvalidFieldValueExceptionWhenInvalidValueInSetter() {
        Clothes clothes = new Pants("Футболка", "M", 500.0, "Cotton");

        assertThrows(InvalidFieldValueException.class, () -> {
            clothes.setPrice(-100.0);
        });
    }

    /**
     * Перевіряє, що конструктор кидає власний виняток при некоректних даних.
     */
    @Test
    void shouldThrowInvalidFieldValueExceptionWhenInvalidConstructorData() {
        assertThrows(InvalidFieldValueException.class, () -> {
            new Pants("", null, -50.0, "");
        });
    }
}