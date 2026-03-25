import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Клас для запуску програми та керування списком одягу через консольне меню.
 */
public class Main {

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Clothes> clothesList = new ArrayList<Clothes>();

        boolean running = true;

        while (running) {
            printMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createClothes(scanner, clothesList);
                    break;
                case 2:
                    printAllClothes(clothesList);
                    break;
                case 3:
                    printObjectsCount();
                    break;
                case 4:
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
        System.out.println("1. Створити новий об’єкт");
        System.out.println("2. Вивести інформацію про всі об’єкти");
        System.out.println("3. Вивести кількість створених об’єктів");
        System.out.println("4. Завершити роботу");
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
     * Створює новий об'єкт одягу та додає його до списку.
     */
    private static void createClothes(Scanner scanner, List<Clothes> clothesList) {
        System.out.println("\nСтворення нового об'єкта:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");

        try {
            Clothes clothes = new Clothes(id, name, size, price);
            clothesList.add(clothes);
            System.out.println("Об'єкт успішно створено.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Виводить інформацію про всі об'єкти.
     */
    private static void printAllClothes(List<Clothes> clothesList) {
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
     * Виводить кількість створених об'єктів.
     */
    private static void printObjectsCount() {
        System.out.println("Кількість створених об'єктів: " + Clothes.getObjectsCount());
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