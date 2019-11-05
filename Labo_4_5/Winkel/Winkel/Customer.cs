using System;

namespace Winkel
{
    public class Customer 
    {
        private int customerNumber;
        private string customerName;
        private string contactLastName;
        private string contactFirstName;
        private string phone;
        private string addressLine1;
        private string addressLine2;
        private string city;
        private string state;
        private string postalCode;
        private string country;
        private int salesRepEmployeeNumber;
        private double creditLimit;


        // hieronder staan alle fields 'encapsulated',
        // kan je automatisch laten genereren via Edit / Refactor / Encapsulate fields
        public string City
        {
            get
            {
                return city;
            }

            set
            {
                city = value;
            }
        }

        public string State
        {
            get
            {
                return state;
            }

            set
            {
                state = value;
            }
        }

        public string PostalCode
        {
            get
            {
                return postalCode;
            }

            set
            {
                postalCode = value;
            }
        }

        public string Country
        {
            get
            {
                return country;
            }

            set
            {
                country = value;
            }
        }

        public int SalesRepEmployeeNumber
        {
            get
            {
                return salesRepEmployeeNumber;
            }

            set
            {
                salesRepEmployeeNumber = value;
            }
        }

        public double CreditLimit
        {
            get
            {
                return creditLimit;
            }

            set
            {
                creditLimit = value;
            }
        }

        public int CustomerNumber
        {
            get
            {
                return customerNumber;
            }

            set
            {
                customerNumber = value;
            }
        }

        public string CustomerName
        {
            get
            {
                return customerName;
            }

            set
            {
                customerName = value;
            }
        }

        public string ContactLastName
        {
            get
            {
                return contactLastName;
            }

            set
            {
                contactLastName = value;
            }
        }

        public string ContactFirstName
        {
            get
            {
                return contactFirstName;
            }

            set
            {
                contactFirstName = value;
            }
        }

        public string Phone
        {
            get
            {
                return phone;
            }

            set
            {
                phone = value;
            }
        }

        public string AddressLine1
        {
            get
            {
                return addressLine1;
            }

            set
            {
                addressLine1 = value;
            }
        }

        public string AddressLine2
        {
            get
            {
                return addressLine2;
            }

            set
            {
                addressLine2 = value;
            }
        }

        override
        public string ToString()
        {
            return customerName+" ["+customerNumber+"||"+contactFirstName+ "|" +ContactLastName+ "|" +city+ "|" +country+ "|" +SalesRepEmployeeNumber+ "|" +creditLimit+ "]";
        }

		public override bool Equals(object obj)
		{
			if (obj == null) return false;
			if (obj.GetType() != this.GetType()) return false;
			var customerDTO = obj as Customer;
			return customerDTO.city.Equals(city) && customerDTO.state.Equals(state) && customerDTO.postalCode.Equals(postalCode)
				&& customerDTO.country.Equals(country) && customerDTO.salesRepEmployeeNumber == salesRepEmployeeNumber && customerDTO.creditLimit == creditLimit
				&& customerDTO.customerNumber == customerNumber && customerDTO.customerName.Equals(customerName) && customerDTO.contactLastName.Equals(contactLastName)
				&& customerDTO.contactFirstName.Equals(contactFirstName) && customerDTO.phone.Equals(phone) && customerDTO.addressLine1.Equals(addressLine1)
				&& customerDTO.addressLine2.Equals(addressLine2);
		}

	}
}
