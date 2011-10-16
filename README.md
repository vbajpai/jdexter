How to Run the Enterprise Archive
---------------------------------

### Introduction

This Wiki helps you quickly demo the project using the built ready-to-deploy enterprise archive.

You still need to configure the Glass Fish Application Server and MySQL though.


### Details

1. Install Glass Fish Open Source Server v3.0.1

2. Install MySQL Community Server v5.x
	* Install the Preference Pane to Toggle Start/Stop of Server (Mac OS X Only)
	* Download MySQL Work Bench
		* Create a New Server Instance
		* Setup a Username and Password: `root:root`
		* Create a DB Schema: `dexter`

3. Push MySQL Connector Jar and Hibernate Library Jars to Glass Fish `lib` Folder
	
4. Changes to Glassfish Admin Console: `http://localhost:4848/`

	* Create a Connection Pool: Resources → JDBC → Connection Pool
 	* Pool Name: `__DexterPool`
	* Resource Type: `javax.sql.ConnectionPoolDataSource`
	* Database Vendor: `MySQL`
	 * Additional Properties
		* User: `root`
		* Password: `root` 
		* Database Name: `dexter`
		* URL: `jdbc:mysql://localhost:3306/dexter`

5. Ping the ConnectionPool (it should succeed at this point)

6. Create a JNDI Name: Resources → JDBC → JDBC Resources 
	* JNDI Name: `jdbc/__Dexter`
	* Enabled: `true` 
	* Connection Pool: `__DexterPool`

7. Start Glass Fish Server

8. Goto - `http://localhost:8080/Dexter`


How to Deploy on Eclipse
------------------------

## Introduction

This page provides a step-by-step overview on how to deploy the project using Eclipse. 

This wiki was written and tested on a Mac OS X machine.

If you're on a different platform the steps would be similar, however your mileage would vary.

## Details

1. Setup Environment

	* Clone the repository 

	* Install Glass Fish Server Adaptor on Eclipse

	* Install Glass Fish Open Source Server v3.0.1

	* Install MySQL Community Server v5.x
		* Install the Preference Pane to Toggle Start/Stop of Server (Mac OS X Only)
		* Download MySQL Work Bench
			* Create a New Server Instance
			* Setup a Username and Password: `root:root`
			* Create a DB Schema: `dexter`

	* Push MySQL Connector Jar to Glass Fish `lib` Folder

	* Create a New Database Connection (use `root:root` and `dexter`) in Eclipse
		* You Need to specify the MySQL Connector Jar Location Here. 

2. Changes to Glassfish Admin Console: `http://localhost:4848/`

	* Create a Connection Pool: Resources → JDBC → Connection Pool
 	* Pool Name: `__DexterPool`
	* Resource Type: `javax.sql.ConnectionPoolDataSource`
	* Database Vendor: `MySQL`
	 * Additional Properties
		* User: `root`
		* Password: `root` 
		* Database Name: `dexter`
		* URL: `jdbc:mysql://localhost:3306/dexter`

3. Ping the ConnectionPool (it should succeed at this point)

4. Create a JNDI Name: Resources → JDBC → JDBC Resources
	* JNDI Name: `jdbc/__Dexter`
	* Enabled: `true`
	* Connection Pool: `__DexterPool`

5. Create an Enterprise Project: `DexterEnterprise`

6. Create a Dynamic Web Project: `Dexter` and Connect to `DexterEnterprise` while Creating	

7. Create an EJB Project: `DexterEJB`
	* Change the EJB Module Version to 3.0

8. Add `DexterEJB` in the Build Path of `DexterWeb` 
	* Struts, JSTL and JSON Libraries are already in `WEB-INF/lib`

9. Push Hibernate Library into Glassfish `lib`

10. Deploy `DexterEnterprise`

11. Run Dexter


Documents
---------

  * [Proposal Slides](https://github.com/downloads/vbajpai/jdexter/proposal_slides.ppt)
  * [Synopsis](https://github.com/downloads/vbajpai/jdexter/synopsis.pdf)
  * [Feasibility Study](https://github.com/downloads/vbajpai/jdexter/feasibility_study.pdf)
  * [Use Cases](https://github.com/downloads/vbajpai/jdexter/usecases.zip)
  * [SRS](https://github.com/downloads/vbajpai/jdexter/srs.pdf)
  * [Class Diagrams](https://github.com/downloads/vbajpai/jdexter/class_diagrams.zip)
  * [Database Designs](https://github.com/downloads/vbajpai/jdexter/database_design.pdf)
  * [Report](https://github.com/downloads/vbajpai/jdexter/report.pdf)
  * [Enterprise Archive](https://github.com/downloads/vbajpai/jdexter/dexter_enterprise.ear)