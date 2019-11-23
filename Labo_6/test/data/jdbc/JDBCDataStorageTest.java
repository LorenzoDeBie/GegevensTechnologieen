package data.jdbc;

import data.DataExceptie;
import data.IDataStorage;
import data.IOrder;
import data.IProduct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


class JDBCDataStorageTest {

    private IDataStorage database;
    @BeforeEach
    void setUp() {
        database = new JDBCDataStorage();
    }

    @Test
    void getProducts() throws DataExceptie {
        List<IProduct> lijst = database.getProducts();
        //er zitten 110 products in de db terwijl ik dit schreef.
        Assertions.assertEquals(110, lijst.size());
        //alle orders checken is beetje te veel vant goeie
    }

    @Test
    void getOrders() {
        //create list manually, add equals to orders, orderdetails --> not today
        int sampleCustomerNumber = 103;
        List<IOrder> expected = new ArrayList<>();

    }

    @Test
    void addOrder() {
    }

    @Test
    void modifyCustomer() {
    }
}
