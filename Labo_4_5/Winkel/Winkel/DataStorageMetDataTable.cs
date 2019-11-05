using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Text;

namespace Winkel
{
	public class DataStorageMetDataTable
	{
		private WinkelDbDataAdapter winkelDbDataAdapter;
		private DataTable data;

		public DataStorageMetDataTable()
		{
			winkelDbDataAdapter = new WinkelDbDataAdapter();
			data = winkelDbDataAdapter.GetCustomerDataTable();
		}

		public void DeleteCustomerByNumberOnNextUpdate(int number)
		{
			data.Rows.Remove(data.Rows.Find(number));
		}

		public void AddCustomerOnNextUpdate(Customer customer)
		{
			data.Rows.Add(customer.CustomerNumber, customer.CustomerName, customer.ContactLastName, customer.ContactLastName, customer.Phone, customer.AddressLine1,
				customer.AddressLine2, customer.City, customer.State, customer.PostalCode, customer.Country, customer.SalesRepEmployeeNumber, customer.CreditLimit);
		}

		public void UpdateCustomerByNumberOnNextUpdate(string fieldsToUpdate, string values, int number)
		{
			DataRow customer = data.Rows.Find(number);
			string[] fields = fieldsToUpdate.Split(';');
			string[] valuesSplit = values.Split(';');
			for(int i = 0; i < fields.Length; i++)
			{
				customer[fields[i]] = valuesSplit[i];
			}
		}
		
		public void UpdateCustomersByFieldsOnNextUpdate(string fieldsToUpdate, string values, string fieldsFilter, string filterValues)
		{
			List<DataRow> rowsToUpdate = new List<DataRow>();
			string[] fieldsToFilter = fieldsFilter.Split(';');
			string[] filterValuesSplit = filterValues.Split(';');
			//look for all the rows to update
			foreach (DataRow row in data.Rows)
			{
				bool inFilter = true;
				for(int i = 0; i < fieldsToFilter.Length; i++)
				{
					if (!row[fieldsToFilter[i]].Equals(filterValuesSplit[i])) inFilter = false;
				}
				if (inFilter) rowsToUpdate.Add(row);
			}		

			//update all rows
			foreach(DataRow row in rowsToUpdate)
			{
				UpdateCustomerByNumberOnNextUpdate(fieldsToUpdate, values, (int) row[WinkelDbDataAdapter.CUSTOMERNUMBER]);
			}

		}

		public void Update()
		{
			winkelDbDataAdapter.Update(data);
		}

		public List<Customer> GetCustomers()
		{
			DataTable tempData = winkelDbDataAdapter.GetCustomerDataTable();
			return GetCustomersFromDataTable(tempData);
		}

		public List<Customer> GetCustomersWithTempChanges()
		{
			return GetCustomersFromDataTable(data);
		}

		private List<Customer> GetCustomersFromDataTable(DataTable tempData)
		{
			List<Customer> result = new List<Customer>();
			foreach (DataRow row in tempData.Rows)
			{
				result.Add(createCustomerFromDataRow(row));
			}
			return result;
		}

		private Customer createCustomerFromDataRow(DataRow row)
		{
			Customer result = new Customer();
			result.CustomerNumber = Convert.ToInt32(row[WinkelDbDataAdapter.CUSTOMERNUMBER]);
			result.CustomerName = row[WinkelDbDataAdapter.CUSTOMERNAME].ToString();
			result.ContactLastName = row[WinkelDbDataAdapter.CONTACTLASTNAME].ToString();
			result.ContactFirstName = row[WinkelDbDataAdapter.CONTACTFIRSTNAME].ToString();
			result.Phone = row[WinkelDbDataAdapter.PHONE].ToString();
			result.AddressLine1 = row[WinkelDbDataAdapter.ADDRESSLINE1].ToString();
			if(row[WinkelDbDataAdapter.ADDRESSLINE2] != DBNull.Value)
				result.AddressLine2 = row[WinkelDbDataAdapter.ADDRESSLINE2].ToString();
			result.City = row[WinkelDbDataAdapter.CITY].ToString();
			if(row[WinkelDbDataAdapter.STATE] != DBNull.Value)
				result.State = row[WinkelDbDataAdapter.STATE].ToString();
			if(row[WinkelDbDataAdapter.POSTALCODE] != DBNull.Value)
				result.PostalCode = row[WinkelDbDataAdapter.POSTALCODE].ToString();
			if (row[WinkelDbDataAdapter.SALESREPEMPLOYEENUMBER] != DBNull.Value)
				result.SalesRepEmployeeNumber = Convert.ToInt32(row[WinkelDbDataAdapter.SALESREPEMPLOYEENUMBER]);
			if (row[WinkelDbDataAdapter.CREDITLIMIT] != DBNull.Value)
				result.CreditLimit = Convert.ToDouble(row[WinkelDbDataAdapter.CREDITLIMIT]);
			return result;
		}


	}
}
