/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package consoleapplicatie;

import data.*;
import data.jdbc.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Testprogramma {

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            IDataStorage database = new JDBCDataStorage();
            List<IOrder> orders = database.getOrders(103);
            System.out.println(orders.size());

        } catch (Exception ex) {
            //
            System.out.println(ex.getMessage());
        }

    }

}
