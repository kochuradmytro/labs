import java.util.ArrayList;
import java.util.Scanner;

/**
 * Клас для запуску програми та керування колекцією одягу через консольне меню.
 */
public class Main {

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager databaseManager;

        if (args.length == 0) {
            System.out.println("Необхідно передати шлях до файлу db.properties через аргументи командного рядка.");
            scanner.close();
            return;
        }

        try {
            databaseManager = new DatabaseManager(args[0]);
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка ініціалізації DatabaseManager: " + e.getMessage());
            scanner.close();
            return;
        }

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createObjectMenu(scanner, databaseManager);
                    break;
                case 2:
                    System.out.println("Роботу програми завершено.");
                    running = false;
                    break;
                case 4:
                    printSortedClothes(databaseManager);
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
        System.out.println("2. Завершити роботу");
        System.out.println("4. Вивести відсортовану інформацію про всі об'єкти");
    }

    /**
     * Зчитує всі об'єкти з бази даних, сортує їх і виводить у консоль.
     */
    private static void printSortedClothes(DatabaseManager databaseManager) {
        ArrayList<Clothes> clothesList = databaseManager.getAllClothes();
        int i;

        if (clothesList.isEmpty()) {
            System.out.println("У базі даних немає об'єктів.");
            return;
        }

        clothesList.sort(null);

        System.out.println("\nВідсортована інформація про всі об'єкти:");
        for (i = 0; i < clothesList.size(); i++) {
            System.out.println(clothesList.get(i));
        }
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
    private static void createObjectMenu(Scanner scanner, DatabaseManager databaseManager) {
        boolean creating = true;

        while (creating) {
            printCreateMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createPants(scanner, databaseManager);
                    creating = false;
                    break;
                case 2:
                    createShirts(scanner, databaseManager);
                    creating = false;
                    break;
                case 3:
                    createJeans(scanner, databaseManager);
                    creating = false;
                    break;
                case 4:
                    createTShirt(scanner, databaseManager);
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
    private static void createPants(Scanner scanner, DatabaseManager databaseManager) {
        System.out.println("\nСтворення об'єкта Pants:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String material = readNonEmptyString(scanner, "Введіть матеріал: ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes pants = new Pants(id, name, size, price, material);
            databaseManager.insertClothes(pants, quantity);
            System.out.println("Об'єкт Pants успішно додано в базу даних.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу Shirts.
     */
    private static void createShirts(Scanner scanner, DatabaseManager databaseManager) {
        System.out.println("\nСтворення об'єкта Shirts:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String sleeveType = readNonEmptyString(scanner, "Введіть тип рукава: ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes shirt = new Shirts(id, name, size, price, sleeveType);
            databaseManager.insertClothes(shirt, quantity);
            System.out.println("Об'єкт Shirts успішно додано в базу даних.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу Jeans.
     */
    private static void createJeans(Scanner scanner, DatabaseManager databaseManager) {
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
            Clothes jeans = new Jeans(id, name, size, price, material, fitType, ripped);
            databaseManager.insertClothes(jeans, quantity);
            System.out.println("Об'єкт Jeans успішно додано в базу даних.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт класу TShirt.
     */
    private static void createTShirt(Scanner scanner, DatabaseManager databaseManager) {
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
            Clothes tShirt = new TShirt(id, name, size, price, sleeveType, printType, sportsStyle);
            databaseManager.insertClothes(tShirt, quantity);
            System.out.println("Об'єкт TShirt успішно додано в базу даних.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
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