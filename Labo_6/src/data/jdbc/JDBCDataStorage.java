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

import javax.xml.crypto.Data;
import java.io.IOError;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCDataStorage implements IDataStorage {

    protected final ResourceBundle sqlOpdrachten;
    protected final ResourceBundle databaseConfig;

    public JDBCDataStorage() {
        sqlOpdrachten = ResourceBundle.getBundle("data.jdbc.sql");
        databaseConfig = ResourceBundle.getBundle("data.jdbc.database");
    }

    private void addOrder(Connection conn, IOrder order) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(sqlOpdrachten.getString("ADD_ORDER"))) {
            stmt.setInt(1, order.getNumber());
            stmt.setDate(2, new java.sql.Date(order.getOrdered().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getRequired().getTime()));
            //can be null and we need to convert first --> check for null
            stmt.setDate(4, order.getShipped() == null ? null : new java.sql.Date(order.getShipped().getTime()));
            stmt.setString(5, order.getStatus());
            //no need to check for null here because we don't have to do anything with it
            stmt.setString(6, order.getComments());
            stmt.setInt(7, order.getCustomerNumber());
            stmt.executeUpdate();
        }
    }

    private void addOrderDetail(Connection conn, IOrderDetail detail) throws SQLException {
        try(PreparedStatement stmt = conn.prepareStatement(sqlOpdrachten.getString("ADD_ORDERDETAIL"))) {
            stmt.setInt(1, detail.getOrderNumber());
            stmt.setString(2, detail.getProductCode());
            stmt.setInt(3, detail.getQuantity());
            stmt.setDouble(4, detail.getPrice());
            stmt.setInt(5, detail.getOrderLineNumber());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<IProduct> getProducts() throws DataExceptie {
        List<IProduct> list = new ArrayList<>();
        try {
            //try met iets tussen haakjes is beetje zoals using in C#
            try(Connection conn = getConnection();
            Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(sqlOpdrachten.getString("Q_PRODUCTS"));
                while(rs.next()) {
                    list.add(new Product(rs.getString(sqlOpdrachten.getString("P_CODE")),
                            rs.getString(sqlOpdrachten.getString("P_NAME")),
                            rs.getString(sqlOpdrachten.getString("P_LINE")),
                            rs.getString(sqlOpdrachten.getString("P_SCALE")),
                            rs.getString(sqlOpdrachten.getString("P_VENDOR")),
                            rs.getString(sqlOpdrachten.getString("P_DESCRIPTION")),
                            rs.getInt(sqlOpdrachten.getString("P_QUANTITYINSTOCK")),
                            rs.getDouble(sqlOpdrachten.getString("P_BUYPRICE")),
                            rs.getDouble(sqlOpdrachten.getString("P_MSRP"))
                    ));
                }
            }
        }
        catch (SQLException e) {
            throw new DataExceptie("Products not available.");
        }
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
        List<IOrder> list = new ArrayList<>();
        try {
            //get order details
            //try met iets tussen haakjes is beetje zoals using in C#
            try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlOpdrachten.getString("Q_ORDERS"))) {
                stmt.setInt(1, customerNumber);
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    //get order details
                    int orderNumber = rs.getInt(sqlOpdrachten.getString("O_NUMBER"));
                    list.add(new Order(
                            orderNumber,
                            rs.getDate(sqlOpdrachten.getString("O_ORDERED")),
                            rs.getDate(sqlOpdrachten.getString("O_REQUIRED")),
                            rs.getDate(sqlOpdrachten.getString("O_SHIPPED")),
                            rs.getString(sqlOpdrachten.getString("O_STATUS")),
                            rs.getString(sqlOpdrachten.getString("O_COMMENTS")),
                            rs.getInt(sqlOpdrachten.getString("O_CNUMBER")),
                            getDetails(orderNumber)
                    ));
                }
            }
        }
        catch (SQLException e) {
            throw new DataExceptie("Products not available.");
        }
        return list;
    }

    @Override
    public boolean addOrder(IOrder order) throws DataExceptie {
        //TODO: maybe check if order exists first and return false when it does?
        try {
            Connection conn = getConnection();
            try {
                conn.setAutoCommit(false);
                // add order first because foreign key on ordernumber with details
                addOrder(conn, order);
                conn.commit();
                //TODO: undo add order when any of these fail?
                for (IOrderDetail detail :
                        order.getDetails()) {
                    if (detail.getOrderNumber() == order.getNumber()) {
                        addOrderDetail(conn, detail);
                    }
                    else {
                        throw new DataExceptie("Orderdetail not part of order!");
                    }
                }
            }
            catch (SQLException ex) {
                conn.rollback();
                throw ex;
            }
            finally {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
        return true;
    }

//    @Override
//    public boolean addCustomer(ICustomer customer) throws DataExceptie {
//        boolean resultaat = false;
//
//        return resultaat;
//    }

    @Override
    public boolean modifyCustomer(ICustomer customer) throws DataExceptie {
        //TODO: check if user exists?
        try {
            try(Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(sqlOpdrachten.getString("U_CUSTOMER"))) {
                stmt.setString(1,customer.getCustomerName());
                stmt.setString(2, customer.getContactLastName());
                stmt.setString(3, customer.getContactFirstName());
                stmt.setString(4, customer.getPhone());
                stmt.setString(5, customer.getAddressLine1());
                stmt.setString(6, customer.getAddressLine2());
                stmt.setString(7, customer.getCity());
                stmt.setString(8, customer.getState());
                stmt.setString(9, customer.getPostalCode());
                stmt.setString(10, customer.getCountry());
                stmt.setInt(11, customer.getSalesRepEmployeeNumber());
                stmt.setDouble(12, customer.getCreditLimit());
                stmt.setInt(13, customer.getCustomerNumber());
                stmt.executeUpdate();
            }
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
        return true;
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

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseConfig.getString("URL"),
                databaseConfig.getString("LOGIN"),
                databaseConfig.getString("PASSWORD"));
    }

    private List<IOrderDetail> getDetails(int orderNumber) throws SQLException {
        List<IOrderDetail> list = new ArrayList<>();
        //try met iets tussen haakjes is beetje zoals using in C#
        try(Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sqlOpdrachten.getString("Q_DETAILS"))) {
            stmt.setInt(1, orderNumber);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                list.add(new OrderDetail(
                        rs.getInt(sqlOpdrachten.getString("OD_ONUMBER")),
                        rs.getString(sqlOpdrachten.getString("OD_PCODE")),
                        rs.getInt(sqlOpdrachten.getString("OD_QUANTITY")),
                        rs.getDouble(sqlOpdrachten.getString("OD_PRICE")),
                        rs.getInt(sqlOpdrachten.getString("OD_OLNUMBER"))
                ));
            }
        }
        return list;
    }
}
