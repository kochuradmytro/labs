import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.UUID;

/**
 * Клас для запуску програми та взаємодії з користувачем.
 */
public class Main {

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Clothes Store");

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createObjectMenu(scanner, store);
                    break;
                case 2:
                    searchByUuid(scanner, store);
                    break;
                case 3:
                    System.out.println("Роботу програми завершено.");
                    running = false;
                    break;
                case 4:
                    sortMenu(scanner, store);
                    break;
                default:
                    System.out.println("Помилка: такого пункту меню не існує.");
            }
        }

        scanner.close();
    }

    /**
     * Виводить головне меню.
     */
    private static void printMainMenu() {
        System.out.println("\nГоловне меню:");
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Пошук об'єкта за UUID");
        System.out.println("3. Завершити роботу");
        System.out.println("4. Вивести відсортовану інформацію про всі об'єкти");
    }

    /**
     * Виводить меню створення об'єкта.
     */
    private static void printCreateMenu() {
        System.out.println("\nОберіть тип об'єкта для створення:");
        System.out.println("1. Pants");
        System.out.println("2. Shirts");
        System.out.println("3. Jeans");
        System.out.println("4. TShirt");
        System.out.println("5. Повернутися до головного меню");
    }

    /**
     * Виводить підменю вибору критерію сортування.
     */
    private static void printSortMenu() {
        System.out.println("\nОберіть критерій сортування:");
        System.out.println("1. Сортувати за назвою");
        System.out.println("2. Сортувати за ціною");
        System.out.println("3. Сортувати за розміром");
        System.out.println("0. Повернутися в головне меню");
    }

    /**
     * Зчитує вибір користувача в меню.
     */
    private static int readMenuChoice(Scanner scanner) {
        while (true) {
            System.out.print("Оберіть пункт меню: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Помилка: рядок не може бути порожнім.");
                continue;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число.");
            }
        }
    }

    /**
     * Обробляє меню створення нового об'єкта.
     */
    private static void createObjectMenu(Scanner scanner, Store store) {
        boolean creating = true;

        while (creating) {
            printCreateMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createPants(scanner, store);
                    creating = false;
                    break;
                case 2:
                    createShirts(scanner, store);
                    creating = false;
                    break;
                case 3:
                    createJeans(scanner, store);
                    creating = false;
                    break;
                case 4:
                    createTShirt(scanner, store);
                    creating = false;
                    break;
                case 5:
                    System.out.println("Повернення до головного меню.");
                    creating = false;
                    break;
                default:
                    System.out.println("Помилка: такого пункту меню не існує.");
            }
        }
    }

    /**
     * Створює об'єкт класу Pants.
     */
    private static void createPants(Scanner scanner, Store store) {
        System.out.println("\nСтворення об'єкта Pants:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String material = readNonEmptyString(scanner, "Введіть матеріал: ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes pants = new Pants(name, size, price, material);
            store.addNewClothes(pants, quantity);
            System.out.println("Об'єкт Pants успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу Shirts.
     */
    private static void createShirts(Scanner scanner, Store store) {
        System.out.println("\nСтворення об'єкта Shirts:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String sleeveType = readNonEmptyString(scanner, "Введіть тип рукава: ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes shirt = new Shirts(name, size, price, sleeveType);
            store.addNewClothes(shirt, quantity);
            System.out.println("Об'єкт Shirts успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу Jeans.
     */
    private static void createJeans(Scanner scanner, Store store) {
        System.out.println("\nСтворення об'єкта Jeans:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String material = readNonEmptyString(scanner, "Введіть матеріал: ");
        String fitType = readNonEmptyString(scanner, "Введіть тип крою: ");
        boolean ripped = readBoolean(scanner, "Є розриви? (true/false): ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes jeans = new Jeans(name, size, price, material, fitType, ripped);
            store.addNewClothes(jeans, quantity);
            System.out.println("Об'єкт Jeans успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу TShirt.
     */
    private static void createTShirt(Scanner scanner, Store store) {
        System.out.println("\nСтворення об'єкта TShirt:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String sleeveType = readNonEmptyString(scanner, "Введіть тип рукава: ");
        String printType = readNonEmptyString(scanner, "Введіть тип принта: ");
        boolean sportsStyle = readBoolean(scanner, "Спортивний стиль? (true/false): ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes tShirt = new TShirt(name, size, price, sleeveType, printType, sportsStyle);
            store.addNewClothes(tShirt, quantity);
            System.out.println("Об'єкт TShirt успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Виконує пошук об'єкта за UUID.
     */
    private static void searchByUuid(Scanner scanner, Store store) {
        ArrayList<Clothes> clothesList = store.getClothesList();
        String input;
        UUID uuid;
        int i;

        if (clothesList.isEmpty()) {
            System.out.println("Список об'єктів порожній.");
            return;
        }

        System.out.print("Введіть UUID для пошуку: ");
        input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Помилка: UUID не може бути порожнім.");
            return;
        }

        try {
            uuid = UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Некоректний формат UUID.");
            return;
        }

        for (i = 0; i < clothesList.size(); i++) {
            if (clothesList.get(i).getUuid().equals(uuid)) {
                System.out.println("Знайдено об'єкт:");
                System.out.println(clothesList.get(i) + ", quantity=" + store.getQuantity(i));
                return;
            }
        }

        System.out.println("Не знайдено.");
    }

    /**
     * Обробляє підменю сортування об'єктів.
     */
    private static void sortMenu(Scanner scanner, Store store) {
        ArrayList<Clothes> clothesList = new ArrayList<Clothes>(store.getClothesList());
        Comparator<Clothes> comparator = null;
        int choice;
        int i;

        if (clothesList.isEmpty()) {
            System.out.println("Список об'єктів порожній.");
            return;
        }

        printSortMenu();
        choice = readMenuChoice(scanner);

        switch (choice) {
            case 1:
                comparator = new Comparator<Clothes>() {
                    @Override
                    public int compare(Clothes o1, Clothes o2) {
                        return o1.getName().compareToIgnoreCase(o2.getName());
                    }
                };
                break;
            case 2:
                comparator = new Comparator<Clothes>() {
                    @Override
                    public int compare(Clothes o1, Clothes o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                };
                break;
            case 3:
                comparator = new Comparator<Clothes>() {
                    @Override
                    public int compare(Clothes o1, Clothes o2) {
                        return o1.getSize().compareToIgnoreCase(o2.getSize());
                    }
                };
                break;
            case 0:
                System.out.println("Повернення в головне меню.");
                return;
            default:
                System.out.println("Помилка: такого пункту меню не існує.");
                return;
        }

        clothesList.sort(comparator);

        System.out.println("\nВідсортована інформація про всі об'єкти:");
        for (i = 0; i < clothesList.size(); i++) {
            System.out.println(clothesList.get(i) + ", quantity=" + store.getQuantityForClothes(clothesList.get(i)));
        }
    }

    /**
     * Зчитує додатне ціле число.
     */
    private static int readPositiveInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Помилка: рядок не може бути порожнім.");
                continue;
            }

            try {
                int value = Integer.parseInt(input);
                if (value <= 0) {
                    System.out.println("Помилка: значення повинно бути більше 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести ціле число.");
            }
        }
    }

    /**
     * Зчитує додатне число типу double.
     */
    private static double readPositiveDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Помилка: рядок не може бути порожнім.");
                continue;
            }

            try {
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println("Помилка: значення повинно бути більше 0.");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Помилка: потрібно ввести число.");
            }
        }
    }

    /**
     * Зчитує непорожній рядок.
     */
    private static String readNonEmptyString(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Помилка: рядок не може бути порожнім.");
                continue;
            }

            return input;
        }
    }

    /**
     * Зчитує логічне значення.
     */
    private static boolean readBoolean(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Помилка: рядок не може бути порожнім.");
                continue;
            }

            if (input.equalsIgnoreCase("true")) {
                return true;
            }

            if (input.equalsIgnoreCase("false")) {
                return false;
            }

            System.out.println("Помилка: введіть true або false.");
        }
    }
}