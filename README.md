#Java Swing CRUD Application with MySQL Database
This project is a simple Java GUI application developed using Swing that allows the user to perform basic CRUD (Create, Read, Update, Delete) operations on a MySQL database. The application has a user-friendly interface with buttons that allow the user to insert, delete, update, and view data in the database.

Features
Create: Add new entries to the database.
Read: View all entries in the database.
Update: Modify existing entries.
Delete: Remove entries from the database.
Technologies Used
Java (JDK 8 or later)
Swing (for the GUI)
JDBC (Java Database Connectivity)
MySQL Database
Prerequisites
Before running the application, you must have the following:

Java JDK installed on your system.
MySQL Server and MySQL Workbench installed.
MySQL JDBC Driver (mysql-connector-java) included in your project’s classpath.

Basic Operations
Insert: Enter the user details and click the "Insert" button to add a new user.
Read/View: Click the "View" button to display all users in the table.
Update: Select a user from the table, modify their details, and click the "Update" button to update their information.
Delete: Select a user from the table and click the "Delete" button to remove the user from the database.
Application Structure
Main.java: Contains the GUI code (Swing) for interacting with the database.
Database.java: Manages database connection and performs CRUD operations using JDBC.
User.java: A model class representing the user object (with fields like id, name, email, age).


# My Project
Here is a screenshot of the application:
Execute Java program that connects to the database using JDBC. Ensure that the console displays a message indicating a successful connection, such as Connected to the database! :
![a1](https://github.com/user-attachments/assets/f6634925-6dde-4eb5-8e2f-1853576a7944)

In the Java Swing application, use an `ActionListener` on the "Insert" button to capture user input and execute an SQL `INSERT` statement via JDBC. After successful insertion, show a popup with the message "Student inserted successfully!" and refresh the displayed data in the table using the "View" button to query and display the updated records.
![a2](https://github.com/user-attachments/assets/bd5e927f-a403-4980-bfc5-0658ef7eedf2)
![a3](https://github.com/user-attachments/assets/cacf1acd-6b5d-47c9-9814-e3fffdceedbf)

After updating the data with the SQL `UPDATE` statement, display a popup saying "Student updated successfully!" and use the "View" button to re-query the database and show the updated records in the table.
![a6](https://github.com/user-attachments/assets/aeb52e37-4dda-481c-8857-1ff659941ff7)
![a7](https://github.com/user-attachments/assets/33e08c6d-ce0b-4b86-a50e-9d357bbe2413)

After executing the SQL `DELETE` statement, display a popup saying "Student deleted successfully!" and use the "View" button to re-query the database and display the updated records in the table.
![a4](https://github.com/user-attachments/assets/a5a53d1c-e44c-4abe-9ba8-1d506ad2318a)
![a5](https://github.com/user-attachments/assets/73eb9163-1434-465c-97f5-9d08c8e08d74)

Execute a `SELECT * FROM table_name` query in MySQL to view the final state of the table after all operations, which will display the all records in the table.
![a8](https://github.com/user-attachments/assets/c27aa5e5-2c5e-4123-9e4f-093a527d5325)


Notes
The application is connected to a MySQL database using JDBC.
Ensure the MySQL server is running before launching the application.
For large datasets, consider adding pagination for the "View" functionality.
