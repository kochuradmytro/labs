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
        ArrayList<Clothes> clothesList = new ArrayList<Clothes>();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createClothes(scanner, clothesList);
                    break;
                case 2:
                    createPants(scanner, clothesList);
                    break;
                case 3:
                    createShirts(scanner, clothesList);
                    break;
                case 4:
                    printAllClothes(clothesList);
                    break;
                case 5:
                    System.out.println("Роботу програми завершено.");
                    running = false;
                    break;
                default:
                    System.out.println("Помилка: такого пункту меню не існує.");
            }
        }

        scanner.close();
    }

    /**
     * Виводить консольне меню.
     */
    private static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Створити об'єкт Clothes");
        System.out.println("2. Створити об'єкт Pants");
        System.out.println("3. Створити об'єкт Shirts");
        System.out.println("4. Вивести інформацію про всі об'єкти");
        System.out.println("5. Завершити роботу");
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
     * Створює об'єкт базового класу Clothes.
     */
    private static void createClothes(Scanner scanner, ArrayList<Clothes> clothesList) {
        System.out.println("\nСтворення об'єкта Clothes:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");

        try {
            Clothes clothes = new Clothes(id, name, size, price);
            clothesList.add(clothes);
            System.out.println("Об'єкт Clothes успішно створено.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт похідного класу Pants.
     */
    private static void createPants(Scanner scanner, ArrayList<Clothes> clothesList) {
        System.out.println("\nСтворення об'єкта Pants:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String material = readNonEmptyString(scanner, "Введіть матеріал: ");

        try {
            Clothes pants = new Pants(id, name, size, price, material);
            clothesList.add(pants);
            System.out.println("Об'єкт Pants успішно створено.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Створює об'єкт похідного класу Shirts.
     */
    private static void createShirts(Scanner scanner, ArrayList<Clothes> clothesList) {
        System.out.println("\nСтворення об'єкта Shirts:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        String sleeveType = readNonEmptyString(scanner, "Введіть тип рукава: ");

        try {
            Clothes shirt = new Shirts(id, name, size, price, sleeveType);
            clothesList.add(shirt);
            System.out.println("Об'єкт Shirts успішно створено.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Виводить інформацію про всі об'єкти колекції.
     */
    private static void printAllClothes(ArrayList<Clothes> clothesList) {
        int i;

        if (clothesList.isEmpty()) {
            System.out.println("Список об'єктів порожній.");
            return;
        }

        System.out.println("\nІнформація про всі об'єкти:");
        for (i = 0; i < clothesList.size(); i++) {
            System.out.println(clothesList.get(i));
        }
    }

    /**
     * Зчитує додатне ціле число з перевіркою коректності введення.
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
     * Зчитує додатне число типу double з перевіркою коректності введення.
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
     * Зчитує непорожній рядок з перевіркою коректності введення.
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
}