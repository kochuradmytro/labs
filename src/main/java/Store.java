import java.util.ArrayList;

/**
 * Клас, що описує магазин одягу.
 */
public class Store {
    private String name;
    private ArrayList<Clothes> clothesList;
    private ArrayList<Integer> quantities;

    /**
     * Створює магазин.
     */
    public Store(String name) {
        setName(name);
        clothesList = new ArrayList<Clothes>();
        quantities = new ArrayList<Integer>();
    }

    /**
     * Повертає назву магазину.
     */
    public String getName() {
        return name;
    }

    /**
     * Встановлює назву магазину.
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва магазину не може бути порожньою.");
        }
        this.name = name.trim();
    }

    /**
     * Повертає колекцію товарів.
     */
    public ArrayList<Clothes> getClothesList() {
        return clothesList;
    }

    /**
     * Повертає кількість товару за індексом.
     */
    public int getQuantity(int index) {
        return quantities.get(index);
    }

    /**
     * Додає новий товар у магазин або збільшує кількість існуючого.
     */
    public void addNewClothes(Clothes cl, int quantity) {
        int i;

        if (cl == null) {
            throw new IllegalArgumentException("Об'єкт одягу не може бути null.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість повинна бути більше 0.");
        }

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).equals(cl)) {
                quantities.set(i, quantities.get(i) + quantity);
                return;
            }
        }

        clothesList.add(cl);
        quantities.add(quantity);
    }

    /**
     * Повертає всі об'єкти, що відповідають вказаному імені.
     */
    public ArrayList<Clothes> searchByName(String name) {
        ArrayList<Clothes> foundList = new ArrayList<Clothes>();
        int i;

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).getName().equalsIgnoreCase(name)) {
                foundList.add(clothesList.get(i));
            }
        }

        return foundList;
    }

    /**
     * Повертає всі об'єкти, що відповідають вказаній ціні.
     */
    public ArrayList<Clothes> searchByPrice(double price) {
        ArrayList<Clothes> foundList = new ArrayList<Clothes>();
        int i;

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).getPrice() == price) {
                foundList.add(clothesList.get(i));
            }
        }

        return foundList;
    }

    /**
     * Повертає всі об'єкти, що відповідають вказаному розміру.
     */
    public ArrayList<Clothes> searchBySize(String size) {
        ArrayList<Clothes> foundList = new ArrayList<Clothes>();
        int i;

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).getSize().equalsIgnoreCase(size)) {
                foundList.add(clothesList.get(i));
            }
        }

        return foundList;
    }

    /**
     * Повертає кількість вказаного товару.
     */
    public int getQuantityForClothes(Clothes clothes) {
        int i;

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).equals(clothes)) {
                return quantities.get(i);
            }
        }

        return 0;
    }
}