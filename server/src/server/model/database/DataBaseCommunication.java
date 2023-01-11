package server.model.database;

import shared.model.Product;
import shared.model.Shoe;

import java.sql.*;
import java.util.ArrayList;

public class DataBaseCommunication {

    private Connection connection;

    public DataBaseCommunication(Connection connection) {

        try {
            this.connection = connection;
            this.connection.setAutoCommit(true);
            System.out.println("[DataBase] Connection made.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("[DataBase] Error. Connection not found.");
        }
    }

    public ArrayList<Product> getProductList(String productId) throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(
                    "select * from skotrekanten.shoe s join skotrekanten.shoeamount ss on s.shoeid = ss.shoeid " +
                        "where s.shoeid = '" + productId + "';");

        while (resultSet.next()) {

            Shoe shoe = new Shoe(
                    resultSet.getDouble("price"),
                    resultSet.getString("location"),
                    resultSet.getString("shoeid"),
                    resultSet.getString("sex").charAt(0),
                    Integer.parseInt(resultSet.getString("sizeid"))
            );
            productList.add(shoe);
        }

        statement.close();
        return productList;
    }

    public ArrayList<Product> getProductList(String productId, int size) throws SQLException {
        ArrayList<Product> productList = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("" +
                "select s.shoeid, s.price, s.sex, s.location, s2.sizeid " +
                "from skotrekanten.shoe s join skotrekanten.shoeamount s2 on s.shoeID = s2.shoeid " +
                "where s.shoeid = '" + productId + "' and s2.sizeid = " + size + ";"
        );

        while (resultSet.next()) {

            Shoe shoe = new Shoe(
                    resultSet.getDouble("price"),
                    resultSet.getString("location"),
                    resultSet.getString("shoeid"),
                    resultSet.getString("sex").charAt(0),
                    Integer.parseInt(resultSet.getString("sizeid"))
            );
            productList.add(shoe);
        }

        statement.close();
        System.out.println(productList);
        return productList;
    }

    public void addExistingProduct(String productId, int size) throws SQLException {
        final String INCREMENT_SHOE_AMOUNT =
                "Update skotrekanten.shoeamount " +
                        "set amount = (" +
                        "select amount from skotrekanten.shoeamount " +
                        "where shoeid = '" + productId + "' " +
                        "and sizeid = " + size +
                        ") + 1 " +
                        "where shoeid = '" + productId + "' " +
                        "and sizeid = " + size + ";";

        PreparedStatement preStatement = connection.prepareStatement(INCREMENT_SHOE_AMOUNT);

        preStatement.executeUpdate();

        System.out.println("[Database] " + preStatement);

        preStatement.close();
    }

    public void addNewProduct(Product product) throws SQLException {
        Shoe shoe = (Shoe) product;

        final String INSERT_SHOE =
                "INSERT INTO skotrekanten.shoe" +
                "(shoeid, size, price, sex, location) VALUES" +
                " (?, ?, ?, ?, ?);";

        PreparedStatement preStatement = connection.prepareStatement(INSERT_SHOE);

        preStatement.setString(1, shoe.id());
        preStatement.setInt(2, shoe.size());
        preStatement.setDouble(3, shoe.price());
        preStatement.setString(4, shoe.sex() + "");
        preStatement.setString(5, shoe.location());

        System.out.println("[Database] " + preStatement);

        preStatement.executeUpdate();

        final String INSERT_SHOEAMOUNT =
                "INSERT INTO skotrekanten.shoeamount" +
                        " (shoeid, sizeid, amount) VALUES" +
                        " (?, ?, ?);";

        preStatement = connection.prepareStatement(INSERT_SHOEAMOUNT);

        preStatement.setString(1, shoe.id());
        preStatement.setInt(2, shoe.size());
        preStatement.setInt(3, 1);

        preStatement.executeUpdate();

        System.out.println("[Database] " + preStatement);
        preStatement.close();
    }

    public void removeExistingProduct(String productId, int size) throws SQLException {
        final String INCREMENT_SHOE_AMOUNT =
            "Update skotrekanten.shoeamount " +
            "set amount = (" +
                    "select amount from skotrekanten.shoeamount " +
                    "where shoeid = '" + productId + "' " +
                    "and sizeid = " + size +
                    ") - 1 " +
            "where shoeid = '" + productId + "' " +
            "and sizeid = " + size + ";";

        PreparedStatement preStatement = connection.prepareStatement(INCREMENT_SHOE_AMOUNT);

        System.out.println("[Database] " + preStatement);

        preStatement.executeUpdate();

        preStatement.close();
    }

    public void removeProduct(String id) throws SQLException {

        final String REMOVE_SHOE =
                "delete from skotrekanten.shoeamount where shoeid = '" + id + "';" +
                "delete from skotrekanten.shoe where shoeid = '" + id + "'";

        PreparedStatement preStatement = connection.prepareStatement(REMOVE_SHOE);

        preStatement.executeUpdate();

        System.out.println("[Database] " + preStatement);
        preStatement.close();
    }

    public void createOrder(ArrayList<Shoe> products) throws SQLException {

        // Getting the latest orderID

        int orderID = 1;

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("" +
                "select * from skotrekanten.ordertable ;"
        );

        while (resultSet.next()) {
            orderID++;
        }

        statement.close();

        // Creates new Order

        String CREATE_ORDER = "insert into skotrekanten.ordertable (orderid) values ("+ orderID +")";

        PreparedStatement createOrder = connection.prepareStatement(CREATE_ORDER);

        createOrder.executeUpdate();

        // Adds things to the basket of the order

        String ADD_ORDER = "insert into skotrekanten.basket (sizeid, shoeid, orderID) values (?, ?, ?) ";

        PreparedStatement preStatement = connection.prepareStatement(ADD_ORDER);

        for (Shoe index : products) {
            preStatement.setInt(1, index.getSize());
            preStatement.setString(2, index.id());
            preStatement.setInt(3,orderID);
            preStatement.addBatch();
        }

        preStatement.executeBatch();

        System.out.println("[Database] " + preStatement);
        preStatement.close();
    }

    public void createReturn(ArrayList<Shoe> products) {
    }
}
