/**
 * Клас, що описує штани.
 */
public class Pants extends Clothes {
    private String material;

    /**
     * Створює об'єкт штанів.
     */
    public Pants(String name, String size, double price, String material) {
        super(name, size, price);
        setMaterial(material);
    }

    /**
     * Повертає матеріал штанів.
     */
    public String getMaterial() {
        return material;
    }

    /**
     * Встановлює матеріал штанів.
     */
    public void setMaterial(String material) {
        if (material == null || material.trim().isEmpty()) {
            throw new InvalidFieldValueException("Матеріал не може бути порожнім.");
        }
        this.material = material.trim();
    }

    /**
     * Повертає дискримінатор типу об'єкта.
     */
    @Override
    public String getType() {
        return "Pants";
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "Pants{" +
                "uuid=" + getUuid() +
                ", name='" + getName() + '\'' +
                ", size='" + getSize() + '\'' +
                ", price=" + getPrice() +
                ", material='" + material + '\'' +
                '}';
    }
}