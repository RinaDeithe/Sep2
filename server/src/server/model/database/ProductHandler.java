package server.model.database;

import shared.model.Product;
import shared.model.Shoe;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductHandler {

    DataBaseCommunication communicator;

    public ProductHandler(Connection connection) {
        communicator = new DataBaseCommunication(connection);
    }

    public ArrayList<Product> getProductList(String productID) throws SQLException {
        return communicator.getProductList(productID);
    }

    public ArrayList<Product> getProductList(String productID, int size) throws SQLException {
        return communicator.getProductList(productID, size);
    }

    public void addProduct(Product product) throws SQLException {
        Shoe shoe = (Shoe) product;
        if (shoe.getLocation().equals("")) {
            communicator.addExistingProduct(shoe.getId(),shoe.getSize());
        }
        else {
            communicator.addNewProduct(product);
        }
    }

    public void removeProduct(String id) throws SQLException {
        communicator.removeProduct(id);
    }

    public void processTransaction(Boolean isReturn, ArrayList<Product> products) throws SQLException {

        ArrayList<Shoe> shoes = translateProductToShoe(products);

        if (!isReturn) {
            communicator.createReturn(shoes);
            for (Shoe index : shoes) {
                communicator.removeExistingProduct(index.getId(), index.getSize());
            }
        }
        else {
            communicator.createOrder(shoes);
            for (Shoe index : shoes) {
                communicator.addExistingProduct(index.getId(), index.getSize());
            }

        }
    }

    private ArrayList<Shoe> translateProductToShoe(ArrayList<Product> product) {
        ArrayList<Shoe> returnList = new ArrayList<>();
        for (Product index : product) {
            returnList.add((Shoe)index);
        }
        return returnList;
    }


    public void addProductList(ArrayList<Product> products) throws SQLException {

        ArrayList<Shoe> shoes = new ArrayList<>(translateProductToShoe(products));

        for (Shoe index : shoes) {
            communicator.addNewProduct(index);
        }
    }
}
