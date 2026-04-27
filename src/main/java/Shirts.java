/**
 * Клас, що описує сорочку.
 */
public class Shirts extends Clothes {
    private String sleeveType;

    /**
     * Створює об'єкт сорочки.
     */
    public Shirts(int id, String name, String size, double price, String sleeveType) {
        super(id, name, size, price);
        setSleeveType(sleeveType);
    }

    /**
     * Повертає тип рукава.
     */
    public String getSleeveType() {
        return sleeveType;
    }

    /**
     * Встановлює тип рукава.
     */
    public void setSleeveType(String sleeveType) {
        if (sleeveType == null || sleeveType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип рукава не може бути порожнім.");
        }
        this.sleeveType = sleeveType.trim();
    }

    /**
     * Повертає дискримінатор типу об'єкта.
     */
    @Override
    public String getType() {
        return "Shirts";
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "Shirts{" +
                "uuid=" + getUuid() +
                ", id=" + getId() +
                ", name='" + getName() + '\'' +
                ", size='" + getSize() + '\'' +
                ", price=" + getPrice() +
                ", sleeveType='" + sleeveType + '\'' +
                '}';
    }
}