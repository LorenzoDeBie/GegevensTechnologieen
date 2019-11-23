/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.jdbc;

import data.DataExceptie;
import data.IDataStorage;
import data.IOrder;
import data.IOrderDetail;
import data.ICustomer;
import data.IProduct;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCDataStorage implements IDataStorage {

    public JDBCDataStorage() throws ClassNotFoundException {

    }

    private void addOrder(Connection conn, IOrder order) throws SQLException {

    }

    private void addOrderDetail(Connection conn, IOrderDetail detail) throws SQLException {

    }

    @Override
    public List<IProduct> getProducts() throws DataExceptie {
        List<IProduct> list = null;

        return list;
    }

//    @Override
//    public List<ICustomer> getCustomers() throws DataExceptie {
//        List<ICustomer> list = null;
//
//        return list;
//    }
//
//    //Lijst van bestellingen zonder details
//    @Override
//    public List<IOrder> getOrders() throws DataExceptie {
//        List<IOrder> list = null;
//
//        return list;
//    }

    //Lijst van bestellingen van een gegeven klant (zonder details)
    @Override
    public List<IOrder> getOrders(int customerNumber) throws DataExceptie {
        List<IOrder> list = null;

        return list;
    }

    @Override
    public boolean addOrder(IOrder order) throws DataExceptie {
        boolean resultaat = false;

        return resultaat;
    }

//    @Override
//    public boolean addCustomer(ICustomer customer) throws DataExceptie {
//        boolean resultaat = false;
//
//        return resultaat;
//    }

    @Override
    public boolean modifyCustomer(ICustomer customer) throws DataExceptie {
        boolean resultaat = false;

        return resultaat;
    }

//    @Override
//    public boolean deleteCustomer(int customerNumber) throws DataExceptie {
//        boolean resultaat = false;
//
//        return resultaat;
//    }
//
//    @Override
//    public double getTotal(int customerNumber) throws DataExceptie {
//        double resultaat = 0;
//
//        return resultaat;
//    }
}
