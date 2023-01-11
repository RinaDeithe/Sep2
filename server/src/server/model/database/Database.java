package server.model.database;

import shared.model.Product;

import java.util.ArrayList;

public interface Database {

    ArrayList<Product> getProductList(String productID);
    ArrayList<Product> getProductList(String productID, int size);
    boolean addProduct(Product product);

    boolean processTransaction(Boolean isReturn, ArrayList<Product> products);

    void createDatabaseConnection();
}
