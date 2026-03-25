import java.util.Scanner;

/**
 * Клас для запуску програми та зчитування даних про одяг з клавіатури.
 */
public class Main {

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = readPositiveInt(scanner, "Введіть кількість елементів масиву: ");
        Clothes[] clothesArray = new Clothes[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nОдяг " + (i + 1));

            int id = readPositiveInt(scanner, "Введіть id: ");
            String name = readNonEmptyString(scanner, "Введіть назву: ");
            String size = readNonEmptyString(scanner, "Введіть розмір: ");
            double price = readPositiveDouble(scanner, "Введіть ціну: ");

            try {
                clothesArray[i] = new Clothes(id, name, size, price);
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка створення об'єкта: " + e.getMessage());
                i--;
            }
        }

        System.out.println("\nІнформація про всі об'єкти:");
        for (Clothes clothes : clothesArray) {
            System.out.println(clothes);
        }

        scanner.close();
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