# Library Management System

## Description
The Library Management System is a simple Java application that allows users to manage a library by performing operations like user registration, book addition, book issuance, book reservation, and fine calculation. The system uses MySQL as the database and connects via JDBC.

## Features
- User Registration
- User Login
- Profile Update
- Add Book
- Delete Book
- Search Book
- Issue Book
- Return Book
- Reserve Book
- Calculate Fine

## Technologies Used
- Java
- MySQL
- JDBC
- Eclipse (IDE)

## Installation

### Prerequisites
- Install [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- Install [MySQL Server](https://dev.mysql.com/downloads/)
- Install [Eclipse IDE](https://www.eclipse.org/downloads/)

### Steps to Run the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/santhosh533/LibraryManagementSystem.git
   ```
2. Open Eclipse and import the project.
3. Configure the MySQL database.
4. Execute the `library.sql` script in MySQL to create the required tables.
5. Update the database credentials in `LibraryManagementSystem.java`:
   ```java
   private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
   private static final String DB_USER = "root";
   private static final String DB_PASSWORD = "your_password";
   ```
6. Run the `LibraryManagementSystem.java` file.

## Database Schema
Execute the following SQL commands to set up the database:
```sql
CREATE DATABASE IF NOT EXISTS library;
USE library;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    issued BOOLEAN DEFAULT FALSE,
    reserved BOOLEAN DEFAULT FALSE
);

CREATE TABLE issued_books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    issue_date DATE NOT NULL,
    return_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);

CREATE TABLE reservations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (book_id) REFERENCES books(id) ON DELETE CASCADE
);
```

## Usage
- Run the application and follow the on-screen prompts to manage books and users.

## License
This project is open-source and available under the MIT License.

## Author
Santhosh

