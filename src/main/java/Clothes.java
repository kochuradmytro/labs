import java.util.UUID;

/**
 * Абстрактний базовий клас, що описує одяг.
 */
public abstract class Clothes implements Comparable<Clothes>, Identifiable {
    private UUID uuid;
    private String name;
    private String size;
    private double price;

    /**
     * Створює об'єкт одягу.
     */
    public Clothes(String name, String size, double price) {
        this.uuid = UUID.randomUUID();
        setName(name);
        setSize(size);
        setPrice(price);
    }

    /**
     * Створює копію іншого об'єкта одягу.
     */
    public Clothes(Clothes other) {
        if (other == null) {
            throw new InvalidFieldValueException("Об'єкт для копіювання не може бути null.");
        }

        this.uuid = UUID.randomUUID();
        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
    }

    /**
     * Повертає UUID об'єкта.
     */
    @Override
    public UUID getUuid() {
        return uuid;
    }

    /**
     * Повертає назву одягу.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву одягу.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidFieldValueException("Назва не може бути порожньою.");
        }
        this.name = name.trim();
    }

    /**
     * Повертає розмір одягу.
     */
    public String getSize() {
        return size;
    }

    /**
     * Встановлює розмір одягу.
     */
    public void setSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            throw new InvalidFieldValueException("Розмір не може бути порожнім.");
        }
        this.size = size.trim();
    }

    /**
     * Повертає ціну одягу.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Встановлює ціну одягу.
     */
    public void setPrice(double price) {
        if (price <= 0) {
            throw new InvalidFieldValueException("Ціна повинна бути більше 0.");
        }
        this.price = price;
    }

    /**
     * Порівнює об'єкти одягу за назвою.
     */
    @Override
    public int compareTo(Clothes other) {
        return this.name.compareToIgnoreCase(other.name);
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "Clothes{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                '}';
    }

    /**
     * Порівнює об'єкти на рівність.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Clothes clothes = (Clothes) obj;

        return Double.compare(price, clothes.price) == 0 &&
                name.equals(clothes.name) &&
                size.equals(clothes.size);
    }

    /**
     * Повертає дискримінатор типу об'єкта.
     */
    public abstract String getType();
}