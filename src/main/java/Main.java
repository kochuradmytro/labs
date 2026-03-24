import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть кількість елементів масиву: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Clothes[] clothesArray = new Clothes[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nОдяг " + (i + 1));

            System.out.print("Введіть id: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Введіть назву: ");
            String name = scanner.nextLine();

            System.out.print("Введіть розмір: ");
            String size = scanner.nextLine();

            System.out.print("Введіть ціну: ");
            double price = scanner.nextDouble();
            scanner.nextLine();

            clothesArray[i] = new Clothes(id, name, size, price);
        }

        System.out.println("\nІнформація про всі об'єкти:");
        for (Clothes clothes : clothesArray) {
            System.out.println(clothes.toString());
        }

        scanner.close();
    }
}