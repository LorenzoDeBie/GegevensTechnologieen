using System.Data.Common;
using System.Configuration;
using System.Data;
using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Text;

namespace Winkel
{
    public abstract class DataStorage
    {

        // Gebruik deze constanten; 
        // zo hoef je niet op juiste spelling te letten
        // en kan je de juiste waarde uit de aangeboden lijst halen 
        // (enkele letters intikken volstaat).
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

        public const string ORDERNUMBER = "orderNumber";
        public const string ORDERDATE = "orderDate";
        public const string REQUIREDDATE = "requiredDate";
        public const string SHIPPEDDATE = "shippedDate";
        public const string STATUS = "status";
        public const string COMMENTS = "comments";

        public const string PRODUCTCODE = "productCode";
        public const string QUANTITYORDERED = "quantityOrdered";
        public const string PRICEEACH = "priceEach";
        public const string ORDERLINENUMBER = "orderLineNumber";

        protected StringBuilder errorMessages = new StringBuilder(); // kan handig zijn; niet verplicht.

		private DbProviderFactory dbProviderFactory;

        public DataStorage()
        {
			dbProviderFactory = DbProviderFactories.GetFactory(ConfigurationManager.ConnectionStrings["winkel"].ProviderName);
		}

        protected DbConnection GetConnection()
        {
			DbConnection connection = dbProviderFactory.CreateConnection();
			connection.ConnectionString = ConfigurationManager.ConnectionStrings["winkel"].ConnectionString;
			return connection;
        }

		protected DbParameter createParam(String key, Object value)
		{
			//TODO: add type checking
			DbParameter parameter = dbProviderFactory.CreateParameter();
			parameter.ParameterName = "@" + key;
			parameter.Value = value;
			return parameter;
		}
		

    }
}
