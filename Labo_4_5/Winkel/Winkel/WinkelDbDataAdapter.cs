using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data.Common;
using System.Configuration;
using System.Data;

namespace Winkel
{
	class WinkelDbDataAdapter
	{
		public const string CUSTOMERNUMBER = "customerNumber";
		public const string CUSTOMERNAME = "customerName";
		public const string CONTACTLASTNAME = "contactLastName";
		public const string CONTACTFIRSTNAME = "contactFirstName";
		public const string PHONE = "phone";
		public const string ADDRESSLINE1 = "addressLine1";
		public const string ADDRESSLINE2 = "addressLine2";
		public const string CITY = "city";
		public const string STATE = "state";
		public const string POSTALCODE = "postalCode";
		public const string COUNTRY = "country";
		public const string SALESREPEMPLOYEENUMBER = "salesRepEmployeeNumber";
		public const string CREDITLIMIT = "creditLimit";
		public const string CUSTOMERTABLENAME = "customers";

		private DbProviderFactory dbProviderFactory;

		private DbDataAdapter dbDataAdapter;

		public WinkelDbDataAdapter()
		{
			dbProviderFactory = DbProviderFactories.GetFactory(ConfigurationManager.ConnectionStrings["winkel"].ProviderName);
			dbDataAdapter = dbProviderFactory.CreateDataAdapter();
			DbConnection connection = dbProviderFactory.CreateConnection();
			connection.ConnectionString = ConfigurationManager.ConnectionStrings["winkel"].ConnectionString;
			//select command
			DbCommand selectCommand = connection.CreateCommand();
			selectCommand.CommandText = ConfigurationManager.AppSettings["SELECT_CUSTOMERS"];
			dbDataAdapter.SelectCommand = selectCommand;
			//update command
			dbDataAdapter.UpdateCommand = MakeCommandWithParams(connection, "UPDATE_CUSTOMER");
			//insert command
			dbDataAdapter.InsertCommand = MakeCommandWithParams(connection, "INSERT_CUSTOMER");
			//delete command
			DbCommand deleteCommand = connection.CreateCommand();
			deleteCommand.CommandText = ConfigurationManager.AppSettings["DELETE_CUSTOMER"];
			dbDataAdapter.DeleteCommand = deleteCommand;
		}

		public DataTable GetCustomerDataTable()
		{
			DataTable result = new DataTable(CUSTOMERTABLENAME);
			dbDataAdapter.Fill(result);
			return result;
		}

		public void Update(DataTable table)
		{
			dbDataAdapter.Update(table);
		}

		private DbCommand MakeCommandWithParams(DbConnection connection, string commandName)
		{
			DbCommand command = connection.CreateCommand();
			command.CommandText = ConfigurationManager.AppSettings[commandName];
			command.Parameters.AddRange(new DbParameter[]
			{
				makeParameter(CUSTOMERNUMBER, DbType.String, CUSTOMERNUMBER, DataRowVersion.Current),
				makeParameter(CUSTOMERNAME, DbType.String, CUSTOMERNAME, DataRowVersion.Current),
				makeParameter(CONTACTLASTNAME, DbType.String, CONTACTLASTNAME, DataRowVersion.Current),
				makeParameter(CONTACTFIRSTNAME, DbType.String, CONTACTFIRSTNAME, DataRowVersion.Current),
				makeParameter(PHONE, DbType.String, PHONE, DataRowVersion.Current),
				makeParameter(ADDRESSLINE1, DbType.String, ADDRESSLINE1, DataRowVersion.Current),
				makeParameter(ADDRESSLINE2, DbType.String, ADDRESSLINE2, DataRowVersion.Current),
				makeParameter(CITY, DbType.String, CITY, DataRowVersion.Current),
				makeParameter(STATE, DbType.String, STATE, DataRowVersion.Current),
				makeParameter(POSTALCODE, DbType.Int32, POSTALCODE, DataRowVersion.Current),
				makeParameter(COUNTRY, DbType.String, COUNTRY, DataRowVersion.Current),
				makeParameter(SALESREPEMPLOYEENUMBER, DbType.Int32, SALESREPEMPLOYEENUMBER, DataRowVersion.Current),
				makeParameter(CREDITLIMIT, DbType.Double, CREDITLIMIT, DataRowVersion.Current),
			});
			return command;
		}

		private DbParameter makeParameter(string paramName, DbType type, string sourceColumn, DataRowVersion sourceVersion)
		{
			DbParameter parameter = dbProviderFactory.CreateParameter();
			parameter.ParameterName = "@" + paramName;
			parameter.DbType = type;
			parameter.SourceColumn = sourceColumn;
			parameter.SourceVersion = sourceVersion;
			return parameter;
		}
		
	}
}