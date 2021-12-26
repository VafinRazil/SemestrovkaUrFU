package com.company;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.ArrayList;

public class DbHandler {

    private static final String CON_STR = "jdbc:sqlite:identifier.sqlite";

    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null)
            instance = new DbHandler();
        return instance;
    }

    private Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CON_STR);
    }


    public void addProduct(Product catalog, int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO " +
                  "products(`productName`, `vendorCode`, `retailPrice`, `number`, `id`) " +
                  "VALUES(?, ?, ?, ?, ?)")) {
            statement.setObject(1, catalog.getNameOfProduct());
            statement.setObject(2, catalog.getArticle());
            statement.setObject(3, catalog.getRetailPrice());
            statement.setObject(4, catalog.getStockBalance());
            statement.setObject(5, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Product> getCatalogsByCondition(String condition) {
        try (Statement statement = this.connection.createStatement()) {
            ArrayList<Product> products = new ArrayList<Product>();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT productName, " +
                            "vendorCode, " +
                            "retailPrice, " +
                            "number " +
                            "FROM products " +
                            ((!condition.isEmpty()) ? condition : ""));
            while (resultSet.next()) {
                products.add(new Product(resultSet.getString("productName"),
                        resultSet.getString("vendorCode"),
                        resultSet.getDouble("retailPrice"),
                        resultSet.getInt("number")));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
