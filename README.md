# Pharmacy_Management_TISBHacks
Code Event: TISB Hacks

Written in Java, JSP, MySQL and CSS

What does the code do?
Currently, pharmacists do not use any management tool to organise the medicines they have and just place them on shelves. This increases the time required to search and find the correct medicines when needed. To solve this problem, our tool provides pharmacists a very straightforward way to add, edit, delete medicines from a database, which includes details like prices, quantity in stock, and on which shelf it's stored.

How to Open:
Download the project zip
1. Have Eclipse IDE/Apache Netbeans, Apache Tomcat 9 or below, MySQL
2. Unzip the folder
3. Create a database called "Pharmacy"
4. Create a table using the code:
   create table medicines(id int primary key not null auto_increment, name varchar(255) not null, description varchar(255) not null, price int not null, stock int not null, shelf varchar(5) not null, category varchar(3) not null);
5. Open the project folder in the IDE
6. Configure Apache Tomcat (or any other server)
7. Run the Program
8. There will be a welcome page with a navbar. The navbar will have a link to the stock management page
9. This stock management page will have an option of adding a new medicine, editing current entries and deleting medicines.
10. It has fields that indicate whether it is a prescription medicine or an over-the-counter medicine.
