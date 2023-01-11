package server.model;

import shared.model.Product;
import shared.util.Subject;

import java.util.ArrayList;

public interface DataModelInterface extends Subject
{
    ArrayList<Product> getProductList(String productID);
    ArrayList<Product> getProductList(String productID, int size);
    boolean addProduct(Product product);

    void processTransaction(Boolean isReturn, ArrayList<Product> productList);

    void createDatabaseConnection();

    ArrayList<Product> getRequests();
    void addRequest(Product product);
    void removeRequest(Product product);
}
