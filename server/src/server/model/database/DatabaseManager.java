package server.model.database;

import shared.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseManager implements Database{

    ProductHandler productHandler;

    public DatabaseManager() {
    }

    @Override
    public ArrayList<Product> getProductList(String productID) {
        try {
            return productHandler.getProductList(productID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Product> getProductList(String productID, int size) {
        try {
            return productHandler.getProductList(productID, size);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean addProduct(Product product) {
        try {
            productHandler.addProduct(product);
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean processTransaction(Boolean isReturn, ArrayList<Product> products) {
        try {
            productHandler.processTransaction(isReturn, products);
            return true;
        } catch (SQLException throwables) {

            return false;
        }

    }

    @Override
    public void createDatabaseConnection() {
        productHandler = new ProductHandler(DatabaseConnection.getInstance().getDbConnection());
    }
}
