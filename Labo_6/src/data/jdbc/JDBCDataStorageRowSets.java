package data.jdbc;

import data.DataExceptie;
import data.IDataStorage;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class JDBCDataStorageRowSets extends JDBCDataStorage {

    private RowSetFactory myRowSetFactory;
    private CachedRowSet officesRowSet;            // primary key niet aanwezig
    private CachedRowSet wijzigbareOfficesRowSet;  // primary key aanwezig
    private CachedRowSet wijzigbareEmployeesRowSet;// primary key aanwezig
    private JoinRowSet jrs;

    public JDBCDataStorageRowSets() throws DataExceptie {
        try {
            myRowSetFactory = RowSetProvider.newFactory();
            officesRowSet = myRowSetFactory.createCachedRowSet();
            wijzigbareOfficesRowSet = myRowSetFactory.createCachedRowSet();
            wijzigbareEmployeesRowSet = myRowSetFactory.createCachedRowSet();

            String commandOffices = sqlOpdrachten.getString("Q_OFFICES");
            officesRowSet.setCommand("select city, phone, addressline1, addressline2, state, country, postalcode, territory, establishedIn from offices");
            wijzigbareOfficesRowSet.setCommand(commandOffices);
            wijzigbareEmployeesRowSet.setCommand(sqlOpdrachten.getString("Q_EMPLOYEES"));

            //officesRowSet.setKeyColumns(new int[]{1});
            wijzigbareOfficesRowSet.setKeyColumns(new int[]{1});
            wijzigbareEmployeesRowSet.setKeyColumns(new int[]{1});

            officesRowSet.execute(getConnection());
            wijzigbareOfficesRowSet.execute(getConnection());
            wijzigbareEmployeesRowSet.execute(getConnection());

            jrs = getEmployeesOfficesSet();
        }
        catch (SQLException ex)
        {
            throw new DataExceptie(ex.getMessage());
        }
    }

    public int countOffices() {
        return officesRowSet.size();
    }

    public void addOffice(String addressline1, String addressline2, String city, String country, String phone, String postalcode, String state, String territory) throws DataExceptie {
        try {
            wijzigbareOfficesRowSet.moveToInsertRow();
            wijzigbareOfficesRowSet.updateInt(1, 0);
            wijzigbareOfficesRowSet.updateString(4, addressline1);
            wijzigbareOfficesRowSet.updateString(5, addressline2);
            wijzigbareOfficesRowSet.updateString(2, city);
            wijzigbareOfficesRowSet.updateString(7, country);
            wijzigbareOfficesRowSet.updateString(3, phone);
            wijzigbareOfficesRowSet.updateString(8, postalcode);
            wijzigbareOfficesRowSet.updateString(6, state);
            wijzigbareOfficesRowSet.updateString(9, territory);
            wijzigbareOfficesRowSet.insertRow();
            wijzigbareOfficesRowSet.moveToCurrentRow();
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
    }

    public void wijzigOfficeDate(int officeCode, Date date) throws DataExceptie {
        //of beter met filteredrowsets?
        try {
            wijzigbareOfficesRowSet.first();
            while(wijzigbareOfficesRowSet.next()) {
                if(wijzigbareOfficesRowSet.getInt(1) == officeCode) {
                    wijzigbareOfficesRowSet.updateDate(sqlOpdrachten.getString("OF_ESTABLISHED"), new java.sql.Date(date.getTime()));
                    return;
                }
            }
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
    }

    public void bewaarAlles() throws DataExceptie {
        try {
            wijzigbareOfficesRowSet.acceptChanges();
            wijzigbareEmployeesRowSet.acceptChanges();
            // no idea how to update mails in database
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
    }

    public Map<String, Set<String>> mailsPerCity() throws DataExceptie {
        Map<String, Set<String>> result = new HashMap<>();
        try {
            jrs.first();
            while (jrs.next()) {
                String city = jrs.getString(sqlOpdrachten.getString("OF_CITY"));
                String mail = jrs.getString(sqlOpdrachten.getString("E_MAIL"));
                //create set if first of city
                if (!result.containsKey(city)) {
                    result.put(city, new HashSet<>());
                }
                result.get(city).add(mail);
            }
        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
        return result;
    }

    public void changeEmailAddresses() throws DataExceptie {
        try {
            while(jrs.next()) {
                String city = jrs.getString(sqlOpdrachten.getString("OF_CITY"));
                String mail = jrs.getString(sqlOpdrachten.getString("E_MAIL"));
                //change email
                String[] mailSplit = mail.split("@");
                String newMail = mailSplit[0] + "@" + city + "." + mailSplit[1];
                jrs.updateString(sqlOpdrachten.getString("E_MAIL"), newMail);
            }

        }
        catch (SQLException ex) {
            throw new DataExceptie(ex.getMessage());
        }
    }

    private JoinRowSet getEmployeesOfficesSet() throws SQLException {
        JoinRowSet jrs = myRowSetFactory.createJoinRowSet();
        jrs.addRowSet(wijzigbareEmployeesRowSet, 6);
        jrs.addRowSet(wijzigbareOfficesRowSet, 1);
        return jrs;
    }

    @Override
    protected Connection getConnection() throws SQLException {
        Connection conn = super.getConnection();
        conn.setAutoCommit(false);
        return conn;
    }
}
