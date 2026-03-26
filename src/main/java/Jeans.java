/**
 * Клас, що описує джинси.
 */
public class Jeans extends Pants {
    private String fitType;
    private boolean ripped;

    /**
     * Створює об'єкт джинсів.
     */
    public Jeans(int id, String name, String size, double price, String material, String fitType, boolean ripped) {
        super(id, name, size, price, material);
        setFitType(fitType);
        this.ripped = ripped;
    }

    /**
     * Повертає тип крою джинсів.
     */
    public String getFitType() {
        return fitType;
    }

    /**
     * Встановлює тип крою джинсів.
     */
    public void setFitType(String fitType) {
        if (fitType == null || fitType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип крою не може бути порожнім.");
        }
        this.fitType = fitType.trim();
    }

    /**
     * Повертає ознаку наявності розривів.
     */
    public boolean isRipped() {
        return ripped;
    }

    /**
     * Встановлює ознаку наявності розривів.
     */
    public void setRipped(boolean ripped) {
        this.ripped = ripped;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "Jeans{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", size='" + getSize() + '\'' +
                ", price=" + getPrice() +
                ", material='" + getMaterial() + '\'' +
                ", fitType='" + fitType + '\'' +
                ", ripped=" + ripped +
                '}';
    }

    @Override
    public String getType() {
        return "Jeans";
    }
}