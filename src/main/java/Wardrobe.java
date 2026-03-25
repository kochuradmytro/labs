import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що агрегує об'єкти Clothes.
 */
public class Wardrobe {
    private String owner;
    private List<Clothes> clothesList;

    /**
     * Створює новий гардероб.
     */
    public Wardrobe(String owner) {
        setOwner(owner);
        this.clothesList = new ArrayList<Clothes>();
    }

    /**
     * Повертає ім'я власника гардероба.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Встановлює ім'я власника гардероба.
     */
    public void setOwner(String owner) {
        if (owner == null || owner.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я власника не може бути порожнім.");
        }
        this.owner = owner.trim();
    }

    /**
     * Додає об'єкт одягу до гардероба.
     */
    public void addClothes(Clothes clothes) {
        if (clothes == null) {
            throw new IllegalArgumentException("Об'єкт одягу не може бути null.");
        }
        clothesList.add(clothes);
    }

    /**
     * Повертає список одягу.
     */
    public List<Clothes> getClothesList() {
        return clothesList;
    }

    /**
     * Повертає кількість речей у гардеробі.
     */
    public int getClothesCount() {
        return clothesList.size();
    }

    /**
     * Повертає рядкове представлення гардероба.
     */
    @Override
    public String toString() {
        return "Wardrobe{" +
                "owner='" + owner + '\'' +
                ", clothesCount=" + clothesList.size() +
                '}';
    }
}