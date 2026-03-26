/**
 * Клас, що описує футболку.
 */
public class TShirt extends Shirts {
    private String printType;
    private boolean sportsStyle;

    /**
     * Створює об'єкт футболки.
     */
    public TShirt(int id, String name, String size, double price, String sleeveType, String printType, boolean sportsStyle) {
        super(id, name, size, price, sleeveType);
        setPrintType(printType);
        this.sportsStyle = sportsStyle;
    }

    /**
     * Повертає тип принта.
     */
    public String getPrintType() {
        return printType;
    }

    /**
     * Встановлює тип принта.
     */
    public void setPrintType(String printType) {
        if (printType == null || printType.trim().isEmpty()) {
            throw new IllegalArgumentException("Тип принта не може бути порожнім.");
        }
        this.printType = printType.trim();
    }

    /**
     * Повертає ознаку спортивного стилю.
     */
    public boolean isSportsStyle() {
        return sportsStyle;
    }

    /**
     * Встановлює ознаку спортивного стилю.
     */
    public void setSportsStyle(boolean sportsStyle) {
        this.sportsStyle = sportsStyle;
    }

    /**
     * Повертає рядкове представлення об'єкта.
     */
    @Override
    public String toString() {
        return "TShirt{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", size='" + getSize() + '\'' +
                ", price=" + getPrice() +
                ", sleeveType='" + getSleeveType() + '\'' +
                ", printType='" + printType + '\'' +
                ", sportsStyle=" + sportsStyle +
                '}';
    }


    @Override
    public String getType() {
        return "TShirt";
    }
}