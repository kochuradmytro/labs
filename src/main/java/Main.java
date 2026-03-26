import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Клас для запуску програми та керування колекцією одягу через консольне меню.
 */
public class Main {

    private static final String FILE_NAME = "input.txt";

    /**
     * Точка входу в програму.
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store store = new Store("Clothes Store");

        loadFromFile(store);

        boolean running = true;

        while (running) {
            printMainMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    createObjectMenu(scanner, store);
                    break;
                case 2:
                    printAllClothes(store);
                    break;
                case 3:
                    searchMenu(scanner, store);
                    break;
                case 4:
                    saveToFile(store);
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
     * Виводить підменю пошуку.
     */
    private static void printSearchMenu() {
        System.out.println("\nПошук об'єкта:");
        System.out.println("1. Пошук за іменем");
        System.out.println("2. Пошук за ціною");
        System.out.println("3. Пошук за розміром");
        System.out.println("4. Повернутися до головного меню");
    }

    /**
     * Обробляє підменю пошуку об'єктів.
     */
    private static void searchMenu(Scanner scanner, Store store) {
        boolean searching = true;

        while (searching) {
            printSearchMenu();
            int choice = readMenuChoice(scanner);

            switch (choice) {
                case 1:
                    searchByName(scanner, store);
                    break;
                case 2:
                    searchByPrice(scanner, store);
                    break;
                case 3:
                    searchBySize(scanner, store);
                    break;
                case 4:
                    System.out.println("Повернення до головного меню.");
                    searching = false;
                    break;
                default:
                    System.out.println("Помилка: такого пункту меню не існує.");
            }
        }
    }

    /**
     * Виконує пошук об'єктів за іменем.
     */
    private static void searchByName(Scanner scanner, Store store) {
        ArrayList<Clothes> foundList;
        String name = readNonEmptyString(scanner, "Введіть ім'я для пошуку: ");

        foundList = store.searchByName(name);
        printSearchResults(foundList, store);
    }

    /**
     * Виконує пошук об'єктів за розміром.
     */
    private static void searchBySize(Scanner scanner, Store store) {
        ArrayList<Clothes> foundList;
        String size = readNonEmptyString(scanner, "Введіть розмір для пошуку: ");

        foundList = store.searchBySize(size);
        printSearchResults(foundList, store);
    }

    /**
     * Виконує пошук об'єктів за ціною.
     */
    private static void searchByPrice(Scanner scanner, Store store) {
        ArrayList<Clothes> foundList;
        double price = readPositiveDouble(scanner, "Введіть ціну для пошуку: ");

        foundList = store.searchByPrice(price);
        printSearchResults(foundList, store);
    }

    /**
     * Виводить результати пошуку.
     */
    private static void printSearchResults(ArrayList<Clothes> foundList, Store store) {
        int i;

        if (foundList.isEmpty()) {
            System.out.println("Жоден об'єкт не відповідає умовам пошуку.");
            return;
        }

        System.out.println("\nЗнайдені об'єкти:");
        for (i = 0; i < foundList.size(); i++) {
            System.out.println(foundList.get(i) + ", quantity=" + store.getQuantityForClothes(foundList.get(i)));
        }
    }

    /**
     * Зчитує об'єкти з файлу та додає їх до магазину.
     */
    private static void loadFromFile(Store store) {
        BufferedReader reader = null;
        String line;
        int lineNumber = 0;

        try {
            reader = new BufferedReader(new FileReader(FILE_NAME));

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.trim().isEmpty()) {
                    continue;
                }

                try {
                    addFromFileLine(store, line);
                } catch (IllegalArgumentException e) {
                    System.out.println("Помилка в рядку " + lineNumber + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Файл " + FILE_NAME + " не знайдено або недоступний для читання.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу після читання.");
                }
            }
        }
    }

    /**
     * Створює об'єкт з рядка файлу та додає його до магазину.
     */
    private static void addFromFileLine(Store store, String line) {
        String[] parts = line.split(";");
        Clothes clothes;
        int quantity;

        if (parts.length < 2) {
            throw new IllegalArgumentException("Недостатньо даних у рядку.");
        }

        try {
            quantity = Integer.parseInt(parts[parts.length - 1].trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Некоректна кількість товару у файлі.");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Кількість товару повинна бути більше 0.");
        }

        clothes = parseClothes(line);
        store.addNewClothes(clothes, quantity);
    }

    /**
     * Записує всі об'єкти з магазину у файл.
     */
    private static void saveToFile(Store store) {
        BufferedWriter writer = null;
        ArrayList<Clothes> clothesList = store.getClothesList();
        int i;

        try {
            writer = new BufferedWriter(new FileWriter(FILE_NAME));

            for (i = 0; i < clothesList.size(); i++) {
                writer.write(toFileString(clothesList.get(i), store.getQuantity(i)));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Помилка запису у файл " + FILE_NAME + ".");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу після запису.");
                }
            }
        }
    }

    /**
     * Створює об'єкт відповідного класу на основі рядка з файлу.
     */
    private static Clothes parseClothes(String line) {
        String[] parts = line.split(";");
        int id;
        double price;
        boolean booleanValue;

        if (parts.length == 0 || parts[0].trim().isEmpty()) {
            throw new IllegalArgumentException("Не вказано тип об'єкта.");
        }

        if (parts[0].equals("Clothes")) {
            if (parts.length != 5 && parts.length != 6) {
                throw new IllegalArgumentException("Некоректна кількість полів для Clothes.");
            }

            try {
                id = Integer.parseInt(parts[1].trim());
                price = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некоректний числовий формат у файлі.");
            }

            return new Clothes(id, parts[2].trim(), parts[3].trim(), price);
        }

        if (parts[0].equals("Pants")) {
            if (parts.length != 6 && parts.length != 7) {
                throw new IllegalArgumentException("Некоректна кількість полів для Pants.");
            }

            try {
                id = Integer.parseInt(parts[1].trim());
                price = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некоректний числовий формат у файлі.");
            }

            return new Pants(id, parts[2].trim(), parts[3].trim(), price, parts[5].trim());
        }

        if (parts[0].equals("Shirts")) {
            if (parts.length != 6 && parts.length != 7) {
                throw new IllegalArgumentException("Некоректна кількість полів для Shirts.");
            }

            try {
                id = Integer.parseInt(parts[1].trim());
                price = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некоректний числовий формат у файлі.");
            }

            return new Shirts(id, parts[2].trim(), parts[3].trim(), price, parts[5].trim());
        }

        if (parts[0].equals("Jeans")) {
            if (parts.length != 8 && parts.length != 9) {
                throw new IllegalArgumentException("Некоректна кількість полів для Jeans.");
            }

            try {
                id = Integer.parseInt(parts[1].trim());
                price = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некоректний числовий формат у файлі.");
            }

            if (parts[7].trim().equalsIgnoreCase("true")) {
                booleanValue = true;
            } else if (parts[7].trim().equalsIgnoreCase("false")) {
                booleanValue = false;
            } else {
                throw new IllegalArgumentException("Некоректне логічне значення у файлі.");
            }

            return new Jeans(id, parts[2].trim(), parts[3].trim(), price, parts[5].trim(), parts[6].trim(), booleanValue);
        }

        if (parts[0].equals("TShirt")) {
            if (parts.length != 8 && parts.length != 9) {
                throw new IllegalArgumentException("Некоректна кількість полів для TShirt.");
            }

            try {
                id = Integer.parseInt(parts[1].trim());
                price = Double.parseDouble(parts[4].trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Некоректний числовий формат у файлі.");
            }

            if (parts[7].trim().equalsIgnoreCase("true")) {
                booleanValue = true;
            } else if (parts[7].trim().equalsIgnoreCase("false")) {
                booleanValue = false;
            } else {
                throw new IllegalArgumentException("Некоректне логічне значення у файлі.");
            }

            return new TShirt(id, parts[2].trim(), parts[3].trim(), price, parts[5].trim(), parts[6].trim(), booleanValue);
        }

        throw new IllegalArgumentException("Невідомий тип об'єкта: " + parts[0]);
    }

    /**
     * Перетворює об'єкт у рядок для запису у файл.
     */
    private static String toFileString(Clothes clothes, int quantity) {
        if (clothes instanceof Jeans) {
            Jeans jeans = (Jeans) clothes;
            return "Jeans;" +
                    jeans.getId() + ";" +
                    jeans.getName() + ";" +
                    jeans.getSize() + ";" +
                    jeans.getPrice() + ";" +
                    jeans.getMaterial() + ";" +
                    jeans.getFitType() + ";" +
                    jeans.isRipped() + ";" +
                    quantity;
        }

        if (clothes instanceof TShirt) {
            TShirt tShirt = (TShirt) clothes;
            return "TShirt;" +
                    tShirt.getId() + ";" +
                    tShirt.getName() + ";" +
                    tShirt.getSize() + ";" +
                    tShirt.getPrice() + ";" +
                    tShirt.getSleeveType() + ";" +
                    tShirt.getPrintType() + ";" +
                    tShirt.isSportsStyle() + ";" +
                    quantity;
        }

        if (clothes instanceof Pants) {
            Pants pants = (Pants) clothes;
            return "Pants;" +
                    pants.getId() + ";" +
                    pants.getName() + ";" +
                    pants.getSize() + ";" +
                    pants.getPrice() + ";" +
                    pants.getMaterial() + ";" +
                    quantity;
        }

        if (clothes instanceof Shirts) {
            Shirts shirt = (Shirts) clothes;
            return "Shirts;" +
                    shirt.getId() + ";" +
                    shirt.getName() + ";" +
                    shirt.getSize() + ";" +
                    shirt.getPrice() + ";" +
                    shirt.getSleeveType() + ";" +
                    quantity;
        }

        return "Clothes;" +
                clothes.getId() + ";" +
                clothes.getName() + ";" +
                clothes.getSize() + ";" +
                clothes.getPrice() + ";" +
                quantity;
    }

    /**
     * Виводить головне меню.
     */
    private static void printMainMenu() {
        System.out.println("\nГоловне меню:");
        System.out.println("1. Створити новий об'єкт");
        System.out.println("2. Вивести інформацію про всі об'єкти");
        System.out.println("3. Пошук об'єкта");
        System.out.println("4. Завершити роботу");
    }

    /**
     * Виводить меню створення об'єкта.
     */
    private static void printCreateMenu() {
        System.out.println("\nОберіть тип об'єкта для створення:");
        System.out.println("1. Clothes");
        System.out.println("2. Pants");
        System.out.println("3. Shirts");
        System.out.println("4. Jeans");
        System.out.println("5. TShirt");
        System.out.println("6. Повернутися до головного меню");
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
                    createClothes(scanner, store);
                    creating = false;
                    break;
                case 2:
                    createPants(scanner, store);
                    creating = false;
                    break;
                case 3:
                    createShirts(scanner, store);
                    creating = false;
                    break;
                case 4:
                    createJeans(scanner, store);
                    creating = false;
                    break;
                case 5:
                    createTShirt(scanner, store);
                    creating = false;
                    break;
                case 6:
                    System.out.println("Повернення до головного меню.");
                    creating = false;
                    break;
                default:
                    System.out.println("Помилка: такого пункту меню не існує.");
            }
        }
    }

    /**
     * Створює об'єкт базового класу Clothes.
     */
    private static void createClothes(Scanner scanner, Store store) {
        System.out.println("\nСтворення об'єкта Clothes:");

        int id = readPositiveInt(scanner, "Введіть id: ");
        String name = readNonEmptyString(scanner, "Введіть назву: ");
        String size = readNonEmptyString(scanner, "Введіть розмір: ");
        double price = readPositiveDouble(scanner, "Введіть ціну: ");
        int quantity = readPositiveInt(scanner, "Введіть кількість: ");

        try {
            Clothes clothes = new Clothes(id, name, size, price);
            store.addNewClothes(clothes, quantity);
            System.out.println("Об'єкт Clothes успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
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
            Clothes pants = new Pants(id, name, size, price, material);
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
            Clothes shirt = new Shirts(id, name, size, price, sleeveType);
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
            Clothes jeans = new Jeans(id, name, size, price, material, fitType, ripped);
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
            Clothes tShirt = new TShirt(id, name, size, price, sleeveType, printType, sportsStyle);
            store.addNewClothes(tShirt, quantity);
            System.out.println("Об'єкт TShirt успішно додано.");
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка створення об'єкта: " + e.getMessage());
        }
    }

    /**
     * Виводить інформацію про всі об'єкти колекції.
     */
    private static void printAllClothes(Store store) {
        ArrayList<Clothes> clothesList = store.getClothesList();
        int i;

        if (clothesList.isEmpty()) {
            System.out.println("Список об'єктів порожній.");
            return;
        }

        System.out.println("\nІнформація про всі об'єкти:");
        for (i = 0; i < clothesList.size(); i++) {
            System.out.println(clothesList.get(i) + ", quantity=" + store.getQuantity(i));
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