package test.server.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.model.database.DataBaseCommunication;
import shared.model.Product;
import shared.model.Shoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DataBaseConnection {
    private DataBaseCommunication dc;
    private Connection connection;

    @BeforeEach
    void createTestEnvironment() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test","postgres" ,"1234");
        autoCreate();
        dc = new DataBaseCommunication(connection);
    }

    @AfterEach
    void endTestEnvironment() throws SQLException {

        PreparedStatement stmt = connection.prepareStatement("drop schema skotrekanten cascade;");

        stmt.executeUpdate();

        connection.close();
    }

    @Test
    void addAndRemoveProductTest() throws SQLException {
        Product product = new Shoe(270,"A1","Test",'f',20);

        dc.addNewProduct(product);

        Product comparator = dc.getProductList("Test").get(0);

        assertEquals(product, comparator);
        dc.removeProduct(product.getId());
    }

    @Test
    void getProductListTest() throws SQLException {
        ArrayList<Product> productList = new ArrayList<>(dc.getProductList("test1"));

        assertFalse(productList.isEmpty());
    }

    @Test
    void getProductListSizeTest() throws SQLException {

        ArrayList<Product> productList = new ArrayList<>(dc.getProductList("test1", 22));

        System.out.println(dc.getProductList("test1", 22));

        assertFalse(productList.isEmpty());
    }

    @Test
    void removeExistingProduct() throws SQLException {
        dc.removeExistingProduct("1", 10);
    }

    @Test
    void addExistingProduct() throws SQLException {
        dc.addExistingProduct("1", 10);
    }

    void autoCreate() {
        System.out.println("[Database] No skotrekanten schema found. Creating new.");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("""
                create schema skotrekanten;
                set search_path = skotrekanten;
                
                create table inventory (
                   location varchar(10) primary key);
                create table shoe (
                    shoeID varchar(10),
                    size int,
                    price decimal(7,2),
                    brand varchar(20),
                    sex varchar(1),
                    location varchar(10),
                    primary key (shoeID),
                    foreign key (location) references inventory(location));
                
                create table size (
                    sizeID int,
                    primary key (sizeID));
                    
                create table orderTable (
                    orderID int,
                    date date,
                    primary key (orderID));
                
                create table basket (
                    sizeID int,
                    shoeID varchar(10),
                    orderID int,
                    foreign key (sizeID) references size(sizeID),
                    foreign key (shoeID) references shoe(shoeID),
                    foreign key (orderID) references ordertable(orderID),
                    primary key (sizeID, shoeID, orderID));
                
                create table shoeAmount (
                    sizeID int,
                    shoeID varchar(10),
                    amount int,
                    foreign key (sizeID) references size(sizeID),
                    foreign key (shoeID) references shoe(shoeID),
                    primary key (sizeID, shoeID));
                
                insert into inventory (location) values
                    ('A1'),('A2'),('A3'),('B1'),('B2'),('B3'),('C1'),('C2'),('C3');
                
                insert into size (sizeid) values
                    (16),(17),(18),(19),(20),(21),(22),(23),(24),(25),
                    (26),(27),(28),(29),(30),(31),(32),(33),(34),(35),
                    (36),(37),(38),(39),(40),(41),(42),(43),(44),(45),
                    (46),(47),(48),(49),(50),(51),(52),(53),(54),(55);
                    
                insert into shoe (shoeID, size, price, brand, sex, location) values
                    ('test1', 36, 450, 'Angulus', 'F', 'A1'),
                    ('test2', 36, 450, 'Angulus', 'F', 'A1'),
                    ('test3', 36, 450, 'Angulus', 'F', 'A2');
                    
                insert into shoeAmount (sizeID, shoeID, amount) VALUES
                    (25,'test1', 4),
                    (22,'test1', 4),
                    (26,'test1', 4),
                    (25,'test2', 4),
                    (30,'test2', 4),
                    (25,'test3', 4);
                    """);

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("[Database] Schema and tables created successfully");
        } catch (SQLException e) {
            System.err.println("[DATABASE WARNING] Could not create schema for database.\n");
        }
    }

}
