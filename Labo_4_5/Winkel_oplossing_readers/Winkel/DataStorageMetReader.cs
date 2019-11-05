using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.Common;
using System.Data.SqlClient;
using System.Linq;
using System.Text;


namespace Winkel
{
    public class DataStorageMetReader : DataStorage
    {


        public List<Customer> GetCustomers()
        {
            List<Customer> list = new List<Customer>();
            DbConnection connection = GetConnection();
            DbCommand command = connection.CreateCommand();
            command.CommandText = ConfigurationManager.AppSettings["SELECT_ALL_CUSTOMERS"];
            connection.Open();
            try
            {
                DbDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    // Strings mogen null zijn in de database; dat veroorzaakt
                    // geen problemen.
                    // Maar int's en double's veroorzaken die wel!
                    Customer c = new Customer();
                    c.AddressLine1 = reader[ADDRESSLINE1].ToString();
                    c.AddressLine2 = reader[ADDRESSLINE2].ToString();
                    c.City = reader[CITY].ToString();
                    c.ContactFirstName = reader[CONTACTFIRSTNAME].ToString();
                    c.ContactLastName = reader[CONTACTLASTNAME].ToString();
                    c.Country = reader[COUNTRY].ToString();
                    if (!(reader[CREDITLIMIT] is System.DBNull))
                    {
                        c.CreditLimit = (Double)reader[CREDITLIMIT]; // double
                    }
                    c.CustomerName = reader[CUSTOMERNAME].ToString();
                    if (!(reader[CUSTOMERNUMBER] is System.DBNull))
                    {
                        c.CustomerNumber = (int)reader[CUSTOMERNUMBER]; // int
                    }
                    c.Phone = reader[PHONE].ToString();
                    c.PostalCode = reader[POSTALCODE].ToString();
                    if (!(reader[SALESREPEMPLOYEENUMBER] is System.DBNull))
                    {
                        c.SalesRepEmployeeNumber = (int)reader[SALESREPEMPLOYEENUMBER];
                    }
                    c.State = reader[STATE].ToString();
                    list.Add(c);
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
            finally
            {
                connection.Close();
            }
            return list;

        }


        public void AddCustomer(Customer customer)
        {
            DbConnection connection = GetConnection();
            DbCommand command = connection.CreateCommand();

            connection.Open(); // niet vergeten!!!

            command.CommandText = ConfigurationManager.AppSettings["INSERT_ONE_CUSTOMER"];

            // naam, value
            command.Parameters.Add(MaakParameter("@" + CUSTOMERNAME, customer.CustomerName));
            command.Parameters.Add(MaakParameter("@" + ADDRESSLINE1, customer.AddressLine1));
            command.Parameters.Add(MaakParameter("@" + ADDRESSLINE2, customer.AddressLine2));
            command.Parameters.Add(MaakParameter("@" + CUSTOMERNUMBER, customer.CustomerNumber));
            command.Parameters.Add(MaakParameter("@" + CONTACTFIRSTNAME, customer.ContactFirstName));
            command.Parameters.Add(MaakParameter("@" + CONTACTLASTNAME, customer.ContactLastName));
            command.Parameters.Add(MaakParameter("@" + PHONE, customer.Phone));
            command.Parameters.Add(MaakParameter("@" + CITY, customer.City));
            command.Parameters.Add(MaakParameter("@" + STATE, customer.State));
            command.Parameters.Add(MaakParameter("@" + POSTALCODE, customer.PostalCode));
            command.Parameters.Add(MaakParameter("@" + COUNTRY, customer.Country));
            command.Parameters.Add(MaakParameter("@" + SALESREPEMPLOYEENUMBER, customer.SalesRepEmployeeNumber));
            command.Parameters.Add(MaakParameter("@" + CREDITLIMIT, customer.CreditLimit));

            try
            {
                command.ExecuteNonQuery();
            }
            catch (SqlException ex)
            {
                for (int i = 0; i < ex.Errors.Count; i++)
                {
                    errorMessages.Append("Index #" + i + "\n" +
                        "Message: " + ex.Errors[i].Message + "\n" +
                        "LineNumber: " + ex.Errors[i].LineNumber + "\n" +
                        "Source: " + ex.Errors[i].Source + "\n" +
                        "Procedure: " + ex.Errors[i].Procedure + "\n");
                }
                Console.WriteLine(errorMessages.ToString());
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }



        // Opgelet! De databank gaat niet na of jouw customernumber wel degelijk bestaat
        // bij de customers... dus je introduceert mogelijks fouten / flaws in de databank.
        public void AddOrder(Order order)
        {
            DbConnection connection = GetConnection();
            connection.Open();

            DbTransaction transaction = connection.BeginTransaction();

            DbCommand command = connection.CreateCommand();
            command.Transaction = transaction;

            command.CommandText = ConfigurationManager.AppSettings["INSERT_ONE_ORDER"];
            command.Parameters.Add(MaakParameter("@" + ORDERNUMBER, order.Number));
            command.Parameters.Add(MaakParameter("@" + ORDERDATE, order.Ordered));
            command.Parameters.Add(MaakParameter("@" + REQUIREDDATE, order.Required));
            command.Parameters.Add(MaakParameter("@" + SHIPPEDDATE, order.Shipped));
            command.Parameters.Add(MaakParameter("@" + STATUS, order.Status));
            command.Parameters.Add(MaakParameter("@" + COMMENTS, order.Comments));
            command.Parameters.Add(MaakParameter("@" + CUSTOMERNUMBER, "" + order.CustomerNumber));


            try
            {
                command.ExecuteNonQuery();
                int i = 0;
                foreach (OrderDetail detail in order.Details)
                {
                    i++;
                    DbCommand commandExtra = connection.CreateCommand();
                    commandExtra.Transaction = transaction;

                    commandExtra.CommandText = ConfigurationManager.AppSettings["INSERT_ORDERDETAILS"];
                    commandExtra.Parameters.Add(MaakParameter("@" + ORDERNUMBER, detail.OrderNumber));
                    commandExtra.Parameters.Add(MaakParameter("@" + ORDERLINENUMBER, detail.OrderLineNumber));
                    commandExtra.Parameters.Add(MaakParameter("@" + PRICEEACH, detail.Price));
                    // Hoe controleer je of de foutopvang goed is?
                    // ANTWOORD:
                    // Ik haal DE REGEL CODE HIERONDER weg, zodat er een fout ontstaat 
                    // - controleer dat Order zelf NIET werd toegevoegd!!
                    // Of, als het via unit test moet: je voegt een detail toe waarvan productcode in
                    // main niet ingevuld is.
                    commandExtra.Parameters.Add(MaakParameter("@" + PRODUCTCODE, detail.ProductCode));
                    commandExtra.Parameters.Add(MaakParameter("@" + QUANTITYORDERED, detail.Quantity));
                    commandExtra.ExecuteNonQuery();
                }
                transaction.Commit();
            }
            catch (SqlException ex)
            {
                transaction.Rollback();
                for (int i = 0; i < ex.Errors.Count; i++)
                {
                    Console.WriteLine(ex.Errors[i].Message);
                }
            }
            catch (Exception ex)
            {
                transaction.Rollback();
                Console.WriteLine(ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }


        /*
        public List<Order> GetOrdersWithDetailsFromCustomer(int customerNumber)
        {
            List<Order> list = GetOrdersWithoutDetailsFromCustomer(customerNumber);
            foreach (Order order in list)
            {
                // nog aan te vullen, is nog niet af...
            }
            return list;
        }
        */

        public List<Order> GetOrdersWithoutDetailsFromCustomer(int customerNumber)
        {
            List<Order> list = new List<Order>();
            DbConnection connection = GetConnection();
            DbCommand command = connection.CreateCommand();
            command.CommandText = ConfigurationManager.AppSettings["SELECT_ORDERS_OF_CUSTOMER"];
            command.Parameters.Add(MaakParameter("@customerNumber", customerNumber));
            connection.Open();
            try
            {
                DbDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    Order ord = new Order();
                    ord.Comments = reader[COMMENTS].ToString();
                    ord.CustomerNumber = (int)reader[CUSTOMERNUMBER];
                    //ord.Details = null;
                    ord.Number = (int)reader[ORDERNUMBER];
                    ord.Ordered = (DateTime)reader[ORDERDATE];
                    ord.Required = (DateTime)reader[REQUIREDDATE];
                    ord.Shipped = (DateTime)reader[SHIPPEDDATE];
                    ord.Status = reader[STATUS].ToString();

                    list.Add(ord);
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("sql-foutje: " + ex.Message);
            }
            finally
            {
                connection.Close();
            }
            return list;
        }

        public void FillDetailsOfOrder(Order order)
        {
            int orderNumber = order.Number;

            DbConnection connection = GetConnection();
            connection.Open();

            DbCommand command = connection.CreateCommand();
            command.CommandText = ConfigurationManager.AppSettings["SELECT_DETAILS_OF_ORDER"];
            command.Parameters.Add(MaakParameter("@orderNumber", orderNumber));
            connection.Open();
            try
            {
                DbDataReader reader = command.ExecuteReader();
                while (reader.Read())
                {
                    OrderDetail detail = new OrderDetail();
                    detail.OrderLineNumber = (int)reader[ORDERLINENUMBER];
                    detail.OrderNumber = orderNumber;
                    detail.Price = (double)reader[PRICEEACH];
                    detail.ProductCode = reader[PRODUCTCODE].ToString();
                    detail.Quantity = (int)reader[QUANTITYORDERED];
                    order.Details.Add(detail);
                }
            }
            catch (SqlException ex)
            {
                Console.WriteLine("sql-foutje: " + ex.Message);
            }
            finally
            {
                connection.Close();
            }
        }







    }
}
