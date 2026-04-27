import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.UUID;

/**
 * Клас-стартер GUI для роботи з колекцією одягу.
 */
public class MainApp extends Application {
    private Store store;
    private ComboBox<String> typeComboBox;
    private TextField nameField;
    private TextField sizeField;
    private TextField priceField;
    private TextField quantityField;
    private TextField materialField;
    private TextField sleeveTypeField;
    private TextField fitTypeField;
    private TextField rippedField;
    private TextField printTypeField;
    private TextField sportsStyleField;
    private ListView<String> clothesListView;
    private TextField searchUuidField;
    private TextArea foundObjectArea;

    /**
     * Запускає JavaFX-застосунок.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Ініціалізує графічний інтерфейс.
     */
    @Override
    public void start(Stage primaryStage) {
        store = new Store("Clothes Store");

        Label typeLabel = new Label("Тип:");
        typeComboBox = new ComboBox<String>();
        typeComboBox.setItems(FXCollections.observableArrayList(
                "Pants",
                "Shirts",
                "Jeans",
                "TShirt"
        ));
        typeComboBox.setValue("Pants");

        Label nameLabel = new Label("Назва:");
        nameField = new TextField();

        Label sizeLabel = new Label("Розмір:");
        sizeField = new TextField();

        Label priceLabel = new Label("Ціна:");
        priceField = new TextField();

        Label quantityLabel = new Label("Кількість:");
        quantityField = new TextField();

        Label materialLabel = new Label("Матеріал:");
        materialField = new TextField();

        Label sleeveTypeLabel = new Label("Тип рукава:");
        sleeveTypeField = new TextField();

        Label fitTypeLabel = new Label("Тип крою:");
        fitTypeField = new TextField();

        Label rippedLabel = new Label("Є розриви (true/false):");
        rippedField = new TextField();

        Label printTypeLabel = new Label("Тип принта:");
        printTypeField = new TextField();

        Label sportsStyleLabel = new Label("Спортивний стиль (true/false):");
        sportsStyleField = new TextField();

        Button addButton = new Button("Додати");
        addButton.setOnAction(event -> addClothes());

        clothesListView = new ListView<String>();
        clothesListView.setPrefHeight(250);

        Label searchUuidLabel = new Label("UUID:");
        searchUuidField = new TextField();

        Button findButton = new Button("Знайти");
        findButton.setOnAction(event -> findByUuid());

        foundObjectArea = new TextArea();
        foundObjectArea.setEditable(false);
        foundObjectArea.setWrapText(true);
        foundObjectArea.setPrefHeight(180);

        typeComboBox.setOnAction(event -> updateFieldsState());
        updateFieldsState();

        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(10);
        formGrid.add(typeLabel, 0, 0);
        formGrid.add(typeComboBox, 1, 0);
        formGrid.add(nameLabel, 0, 1);
        formGrid.add(nameField, 1, 1);
        formGrid.add(sizeLabel, 0, 2);
        formGrid.add(sizeField, 1, 2);
        formGrid.add(priceLabel, 0, 3);
        formGrid.add(priceField, 1, 3);
        formGrid.add(quantityLabel, 0, 4);
        formGrid.add(quantityField, 1, 4);
        formGrid.add(materialLabel, 0, 5);
        formGrid.add(materialField, 1, 5);
        formGrid.add(sleeveTypeLabel, 0, 6);
        formGrid.add(sleeveTypeField, 1, 6);
        formGrid.add(fitTypeLabel, 0, 7);
        formGrid.add(fitTypeField, 1, 7);
        formGrid.add(rippedLabel, 0, 8);
        formGrid.add(rippedField, 1, 8);
        formGrid.add(printTypeLabel, 0, 9);
        formGrid.add(printTypeField, 1, 9);
        formGrid.add(sportsStyleLabel, 0, 10);
        formGrid.add(sportsStyleField, 1, 10);
        formGrid.add(addButton, 1, 11);

        GridPane searchGrid = new GridPane();
        searchGrid.setHgap(10);
        searchGrid.setVgap(10);
        searchGrid.add(searchUuidLabel, 0, 0);
        searchGrid.add(searchUuidField, 1, 0);
        searchGrid.add(findButton, 2, 0);

        VBox root = new VBox(15);
        root.setPadding(new Insets(15));
        root.getChildren().addAll(
                new Label("Додавання об'єктів у колекцію"),
                formGrid,
                new Label("Колекція (скорочено):"),
                clothesListView,
                new Label("Пошук об'єкта за UUID:"),
                searchGrid,
                new Label("Повна інформація про знайдений об'єкт:"),
                foundObjectArea
        );

        Scene scene = new Scene(root, 650, 860);
        primaryStage.setTitle("Clothes Store");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Оновлює доступність полів залежно від типу об'єкта.
     */
    private void updateFieldsState() {
        String type = typeComboBox.getValue();

        materialField.setDisable(true);
        sleeveTypeField.setDisable(true);
        fitTypeField.setDisable(true);
        rippedField.setDisable(true);
        printTypeField.setDisable(true);
        sportsStyleField.setDisable(true);

        if ("Pants".equals(type)) {
            materialField.setDisable(false);
        } else if ("Shirts".equals(type)) {
            sleeveTypeField.setDisable(false);
        } else if ("Jeans".equals(type)) {
            materialField.setDisable(false);
            fitTypeField.setDisable(false);
            rippedField.setDisable(false);
        } else if ("TShirt".equals(type)) {
            sleeveTypeField.setDisable(false);
            printTypeField.setDisable(false);
            sportsStyleField.setDisable(false);
        }
    }

    /**
     * Створює об'єкт вибраного типу та додає його в колекцію.
     */
    private void addClothes() {
        String type = typeComboBox.getValue();
        double price;
        int quantity;

        try {
            price = Double.parseDouble(priceField.getText().trim());
            quantity = Integer.parseInt(quantityField.getText().trim());

            Clothes clothes;

            if ("Pants".equals(type)) {
                clothes = new Pants(
                        nameField.getText().trim(),
                        sizeField.getText().trim(),
                        price,
                        materialField.getText().trim()
                );
            } else if ("Shirts".equals(type)) {
                clothes = new Shirts(
                        nameField.getText().trim(),
                        sizeField.getText().trim(),
                        price,
                        sleeveTypeField.getText().trim()
                );
            } else if ("Jeans".equals(type)) {
                clothes = new Jeans(
                        nameField.getText().trim(),
                        sizeField.getText().trim(),
                        price,
                        materialField.getText().trim(),
                        fitTypeField.getText().trim(),
                        parseBooleanValue(rippedField.getText().trim())
                );
            } else {
                clothes = new TShirt(
                        nameField.getText().trim(),
                        sizeField.getText().trim(),
                        price,
                        sleeveTypeField.getText().trim(),
                        printTypeField.getText().trim(),
                        parseBooleanValue(sportsStyleField.getText().trim())
                );
            }

            store.addNewClothes(clothes, quantity);
            updateListView();
            clearFields();
        } catch (NumberFormatException e) {
            showError("Ціна та кількість повинні бути числовими значеннями.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    /**
     * Виконує пошук об'єкта за UUID.
     */
    private void findByUuid() {
        String input = searchUuidField.getText().trim();
        UUID uuid;
        Clothes foundClothes;
        int quantity;

        if (input.isEmpty()) {
            showError("UUID не може бути порожнім.");
            return;
        }

        try {
            uuid = UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            showError("Некоректний формат UUID.");
            return;
        }

        foundClothes = findClothesByUuid(uuid);

        if (foundClothes == null) {
            foundObjectArea.setText("не знайдено");
            return;
        }

        quantity = store.getQuantityForClothes(foundClothes);
        foundObjectArea.setText(foundClothes.toString() + System.lineSeparator() + "quantity=" + quantity);
    }

    /**
     * Повертає об'єкт за UUID або null, якщо об'єкт не знайдено.
     */
    private Clothes findClothesByUuid(UUID uuid) {
        int i;

        for (i = 0; i < store.getClothesList().size(); i++) {
            if (store.getClothesList().get(i).getUuid().equals(uuid)) {
                return store.getClothesList().get(i);
            }
        }

        return null;
    }

    /**
     * Перетворює рядок у логічне значення.
     */
    private boolean parseBooleanValue(String value) {
        if (value.equalsIgnoreCase("true")) {
            return true;
        }

        if (value.equalsIgnoreCase("false")) {
            return false;
        }

        throw new IllegalArgumentException("Введіть true або false.");
    }

    /**
     * Оновлює скорочене відображення всієї колекції.
     */
    private void updateListView() {
        int i;

        clothesListView.getItems().clear();

        for (i = 0; i < store.getClothesList().size(); i++) {
            Clothes clothes = store.getClothesList().get(i);
            clothesListView.getItems().add(
                    clothes.getType() + ": " +
                            clothes.getName() +
                            " | UUID: " + clothes.getUuid()
            );
        }
    }

    /**
     * Очищає поля введення після додавання.
     */
    private void clearFields() {
        nameField.clear();
        sizeField.clear();
        priceField.clear();
        quantityField.clear();
        materialField.clear();
        sleeveTypeField.clear();
        fitTypeField.clear();
        rippedField.clear();
        printTypeField.clear();
        sportsStyleField.clear();
    }

    /**
     * Показує повідомлення про помилку.
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}