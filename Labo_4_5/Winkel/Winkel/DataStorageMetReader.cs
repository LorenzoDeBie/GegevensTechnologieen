using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
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
			// ...
			List<Customer> result = new List<Customer>();
			DbConnection connection = GetConnection();
			try
			{
				DbCommand command = connection.CreateCommand();
				command.CommandText = ConfigurationManager.AppSettings["SELECT_CUSTOMERS"];
				connection.Open();
				DbDataReader reader = command.ExecuteReader();
				
				while(reader.Read())
				{
					Customer current = new Customer();
					if(!(reader[CUSTOMERNUMBER] is DBNull))
						current.CustomerNumber = (int)reader[CUSTOMERNUMBER];
					current.CustomerName = reader[CUSTOMERNAME].ToString();
					current.ContactLastName = reader[CONTACTLASTNAME].ToString();
					current.ContactFirstName = reader[CONTACTFIRSTNAME].ToString();
					current.Phone = reader[PHONE].ToString();
					current.AddressLine1 = reader[ADDRESSLINE1].ToString();
					current.AddressLine2 = reader[ADDRESSLINE2].ToString();
					current.City = reader[CITY].ToString();
					current.State = reader[STATE].ToString();
					current.PostalCode = reader[POSTALCODE].ToString();
					current.Country = reader[COUNTRY].ToString();
					if (!(reader[SALESREPEMPLOYEENUMBER] is DBNull))
						current.SalesRepEmployeeNumber = (int)reader[SALESREPEMPLOYEENUMBER];
					if(!(reader[CREDITLIMIT] is DBNull))
						current.CreditLimit = (double)reader[CREDITLIMIT];
					result.Add(current);
				}
			}
			catch(SqlException ex)
			{
				errorMessages.Append(ex.Message);
				Console.WriteLine(errorMessages.ToString());
			}
			finally
			{
				connection.Close();
			}
			return result;
        }

        public void AddCustomer(Customer customer)
        {
			DbConnection connection = GetConnection();
			try
			{
				DbCommand command = connection.CreateCommand();
				command.CommandText = ConfigurationManager.AppSettings["ADD_CUSTOMER"];
				command.Parameters.Add(createParam(CUSTOMERNUMBER, customer.CustomerNumber));
				command.Parameters.Add(createParam(CUSTOMERNAME, customer.CustomerName));
				command.Parameters.Add(createParam(CONTACTLASTNAME, customer.ContactLastName));
				command.Parameters.Add(createParam(CONTACTFIRSTNAME, customer.ContactFirstName));
				command.Parameters.Add(createParam(PHONE, customer.Phone));
				command.Parameters.Add(createParam(ADDRESSLINE1, customer.AddressLine1));
				command.Parameters.Add(createParam(ADDRESSLINE2, customer.AddressLine2));
				command.Parameters.Add(createParam(CITY, customer.City));
				command.Parameters.Add(createParam(STATE, customer.State));
				command.Parameters.Add(createParam(POSTALCODE, customer.PostalCode));
				command.Parameters.Add(createParam(COUNTRY, customer.Country));
				command.Parameters.Add(createParam(SALESREPEMPLOYEENUMBER, customer.SalesRepEmployeeNumber));
				command.Parameters.Add(createParam(CREDITLIMIT, customer.CreditLimit));
				connection.Open();
				command.ExecuteNonQuery();
			}
			catch(Exception ex)
			{
				errorMessages.AppendLine(ex.ToString());
				Console.Error.WriteLine(errorMessages.ToString());
			}
			finally
			{
				connection.Close();
			}
        }

        public void AddOrder(Order order)
        {
			DbConnection connection = GetConnection();
			//use transaction because if one query fails we need to rollback
			using(DbTransaction transaction = connection.BeginTransaction())
			{
				try
				{
					using (DbCommand command = connection.CreateCommand())
					{
						command.CommandText = ConfigurationManager.AppSettings["ADD_ORDERDETAIL"];
						command.Parameters.Add(createParam(PRODUCTCODE, null));
						command.Parameters.Add(createParam(QUANTITYORDERED, null));
						command.Parameters.Add(createParam(PRICEEACH, null));
						command.Parameters.Add(createParam(ORDERLINENUMBER, null));
						//add all orderdetails to db
						foreach (OrderDetail detail in order.Details)
						{
							command.Parameters[PRODUCTCODE].Value = detail.ProductCode;
							command.Parameters[QUANTITYORDERED].Value = detail.Quantity;
							command.Parameters[PRICEEACH].Value = detail.Price;
							command.Parameters[ORDERLINENUMBER].Value = detail.OrderLineNumber;
							command.ExecuteNonQuery();
						}
					}
					//add the order itself
					using(DbCommand command = connection.CreateCommand())
					{
						command.CommandText = ConfigurationManager.AppSettings["ADD_ORDER"];
						command.Parameters.Add(createParam(ORDERNUMBER, order.Number));
						command.Parameters.Add(createParam(ORDERDATE, order.Ordered));
						command.Parameters.Add(createParam(REQUIREDDATE, order.Required));
						command.Parameters.Add(createParam(SHIPPEDDATE, order.Shipped));
						command.Parameters.Add(createParam(STATUS, order.Status));
						command.Parameters.Add(createParam(COMMENTS, order.Comments));
						command.ExecuteNonQuery();
					}
				}
				catch(Exception ex)
				{
					transaction.Rollback();
					errorMessages.Append("Failed to add order to database!");
					Console.Error.WriteLine(errorMessages.ToString());
				}
			}
        }
    }
}
