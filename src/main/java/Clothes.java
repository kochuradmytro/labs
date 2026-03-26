/**
 * Базовий клас, що описує одяг.
 */
public abstract class Clothes {
    private int id;
    private String name;
    private String size;
    private double price;

    /**
     * Створює об'єкт одягу з перевіркою коректності даних.
     */
    public Clothes(int id, String name, String size, double price) {
        setId(id);
        setName(name);
        setSize(size);
        setPrice(price);
    }

    /**
     * Створює копію іншого об'єкта одягу.
     */
    public Clothes(Clothes other) {
        if (other == null) {
            throw new IllegalArgumentException("Об'єкт для копіювання не може бути null.");
        }

        this.id = other.id;
        this.name = other.name;
        this.size = other.size;
        this.price = other.price;
    }

    /**
     * Повертає ідентифікатор одягу.
     */
    public int getId() {
        return id;
    }

    /**
     * Встановлює ідентифікатор одягу.
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id повинен бути більше 0.");
        }
        this.id = id;
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
            throw new IllegalArgumentException("Назва не може бути порожньою.");
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
            throw new IllegalArgumentException("Розмір не може бути порожнім.");
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
            throw new IllegalArgumentException("Ціна повинна бути більше 0.");
        }
        this.price = price;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "Clothes{" +
                "id=" + id +
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

        return id == clothes.id &&
                Double.compare(price, clothes.price) == 0 &&
                name.equals(clothes.name) &&
                size.equals(clothes.size);
    }

    public abstract String getType();
}