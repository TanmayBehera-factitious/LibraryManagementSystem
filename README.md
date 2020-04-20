# LibraryManagementSystem
A simple console application of Library Management System developed using java.
### Prequisites to run the application
* JAVA
* MySQL Community Server
* MySQL JDBC Connector

### Intial Database setup
1. Crete a database test and select it.
2. Create 2 tables AccountInformation('Username' varchar(20), 'Password' varchar(20), 'Admin/User' varchar(20)) and 
   Book_Details('Username' varchar(20), 'Book_name' varchar(20), 'Status' varchar(20), 'Date_of_Issue' date, 'Date_of_return' varchar(20))
3. Add an Admin user so, to access the database from the app.
###### All required database queries can be found in QUERIES.txt.  

#### Running the application
Run the Main.class file in bin/ directory using the JVM. Or you can compile the Main.java file in src/ directory.
