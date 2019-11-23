/******************************************************************************
 * Copyright (c) 2005 Actuate Corporation.
 * All rights reserved. This file and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Actuate Corporation  - initial implementation
 *
 * Classic Models Inc. sample database developed as part of the
 * Eclipse BIRT Project. For more information, see http:\\www.eclipse.org\birt
 *
 *******************************************************************************/

/* Recommended DATABASE name is classicmodels. */

USE classicmodels;

/* DROP the existing tables. Comment this out if it is not needed. */	
/*
DROP TABLE Customers;
*/
DROP TABLE Employees;
DROP TABLE Offices;
/*
DROP TABLE OrderDetails;
DROP TABLE Orders;
DROP TABLE Payments;
DROP TABLE Products;
*/

/* Create the full set of Classic Models Tables */


CREATE TABLE Employees (
  employeeNumber INTEGER NOT NULL,
  lastName VARCHAR(50) NOT NULL,
  firstName VARCHAR(50) NOT NULL,
  extension VARCHAR(10) NOT NULL,
  email VARCHAR(100) NOT NULL,
  officeCode INTEGER NOT NULL,
  reportsTo INTEGER NULL,
  jobTitle VARCHAR(50) NOT NULL,
  PRIMARY KEY (employeeNumber)
);

CREATE TABLE Offices (
  officeCode INTEGER AUTO_INCREMENT PRIMARY KEY,
  city VARCHAR(50) NOT NULL,
  phone VARCHAR(50) NOT NULL,
  addressLine1 VARCHAR(50) NOT NULL,
  addressLine2 VARCHAR(50) NULL,
  state VARCHAR(50) NULL,
  country VARCHAR(50) NOT NULL,
  postalCode VARCHAR(10) NOT NULL,
  territory VARCHAR(10) NOT NULL,
  establishedIn DATE
);
GRANT ALL PRIVILEGES ON classicmodels.* TO 'gast'@'localhost' IDENTIFIED BY 'gast' WITH GRANT OPTION;