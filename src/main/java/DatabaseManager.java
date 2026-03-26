import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Клас для роботи з базою даних.
 */
public class DatabaseManager {
    private String url;
    private String user;
    private String password;

    /**
     * Завантажує параметри підключення з файлу властивостей.
     */
    public DatabaseManager(String propertiesPath) {
        Properties properties = new Properties();
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(propertiesPath);
            properties.load(inputStream);

            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");

            if (url == null || user == null || password == null) {
                throw new IllegalArgumentException("У файлі властивостей відсутні параметри підключення.");
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Не вдалося прочитати файл властивостей: " + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    System.out.println("Помилка закриття файлу властивостей.");
                }
            }
        }
    }

    /**
     * Повертає нове підключення до бази даних.
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Додає об'єкт одягу в базу даних.
     */
    public void insertClothes(Clothes clothes, int quantity) {
        String sql =
                "INSERT INTO clothes_store " +
                "(id, type, name, size, price, quantity, material, sleeve_type, fit_type, ripped, print_type, sports_style) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, clothes.getId());
            preparedStatement.setString(2, clothes.getType());
            preparedStatement.setString(3, clothes.getName());
            preparedStatement.setString(4, clothes.getSize());
            preparedStatement.setDouble(5, clothes.getPrice());
            preparedStatement.setInt(6, quantity);

            if (clothes instanceof Jeans) {
                Jeans jeans = (Jeans) clothes;
                preparedStatement.setString(7, jeans.getMaterial());
                preparedStatement.setNull(8, java.sql.Types.VARCHAR);
                preparedStatement.setString(9, jeans.getFitType());
                preparedStatement.setBoolean(10, jeans.isRipped());
                preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                preparedStatement.setNull(12, java.sql.Types.BOOLEAN);
            } else if (clothes instanceof TShirt) {
                TShirt tShirt = (TShirt) clothes;
                preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                preparedStatement.setString(8, tShirt.getSleeveType());
                preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                preparedStatement.setNull(10, java.sql.Types.BOOLEAN);
                preparedStatement.setString(11, tShirt.getPrintType());
                preparedStatement.setBoolean(12, tShirt.isSportsStyle());
            } else if (clothes instanceof Pants) {
                Pants pants = (Pants) clothes;
                preparedStatement.setString(7, pants.getMaterial());
                preparedStatement.setNull(8, java.sql.Types.VARCHAR);
                preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                preparedStatement.setNull(10, java.sql.Types.BOOLEAN);
                preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                preparedStatement.setNull(12, java.sql.Types.BOOLEAN);
            } else if (clothes instanceof Shirts) {
                Shirts shirts = (Shirts) clothes;
                preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                preparedStatement.setString(8, shirts.getSleeveType());
                preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                preparedStatement.setNull(10, java.sql.Types.BOOLEAN);
                preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                preparedStatement.setNull(12, java.sql.Types.BOOLEAN);
            } else {
                preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                preparedStatement.setNull(8, java.sql.Types.VARCHAR);
                preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                preparedStatement.setNull(10, java.sql.Types.BOOLEAN);
                preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                preparedStatement.setNull(12, java.sql.Types.BOOLEAN);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Помилка SQL під час додавання об'єкта: " + e.getMessage());
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Помилка закриття PreparedStatement.");
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Помилка закриття з'єднання з базою даних.");
                }
            }
        }
    }

    /**
     * Повертає список усіх об'єктів з бази даних.
     */
    public ArrayList<Clothes> getAllClothes() {
        String sql = "SELECT * FROM clothes_store";
        ArrayList<Clothes> clothesList = new ArrayList<Clothes>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String size = resultSet.getString("size");
                double price = resultSet.getDouble("price");

                if (type.equals("Pants")) {
                    clothesList.add(new Pants(
                            id,
                            name,
                            size,
                            price,
                            resultSet.getString("material")
                    ));
                } else if (type.equals("Shirts")) {
                    clothesList.add(new Shirts(
                            id,
                            name,
                            size,
                            price,
                            resultSet.getString("sleeve_type")
                    ));
                } else if (type.equals("Jeans")) {
                    clothesList.add(new Jeans(
                            id,
                            name,
                            size,
                            price,
                            resultSet.getString("material"),
                            resultSet.getString("fit_type"),
                            resultSet.getBoolean("ripped")
                    ));
                } else if (type.equals("TShirt")) {
                    clothesList.add(new TShirt(
                            id,
                            name,
                            size,
                            price,
                            resultSet.getString("sleeve_type"),
                            resultSet.getString("print_type"),
                            resultSet.getBoolean("sports_style")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Помилка SQL під час зчитування об'єктів: " + e.getMessage());
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("Помилка закриття ResultSet.");
                }
            }

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    System.out.println("Помилка закриття PreparedStatement.");
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Помилка закриття з'єднання з базою даних.");
                }
            }
        }

        return clothesList;
    }
}