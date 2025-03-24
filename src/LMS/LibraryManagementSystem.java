package LMS;

import java.sql.*;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "@Santhosh420";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Update Profile");
            System.out.println("4. Add Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Search Book");
            System.out.println("7. Issue Book");
            System.out.println("8. Return Book");
            System.out.println("9. Reserve Book");
            System.out.println("10. Calculate Fine");
            System.out.println("11. Exit");
            System.out.print("Choose an option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    loginUser(scanner);
                    break;
                case 3:
                    updateProfile(scanner);
                    break;
                case 4:
                    addBook(scanner);
                    break;
                case 5:
                    deleteBook(scanner);
                    break;
                case 6:
                    searchBook(scanner);
                    break;
                case 7:
                    issueBook(scanner);
                    break;
                case 8:
                    returnBook(scanner);
                    break;
                case 9:
                    reserveBook(scanner);
                    break;
                case 10:
                    calculateFine(scanner);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
    
    private static void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.executeUpdate();
            System.out.println("User registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void loginUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid credentials!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void updateProfile(Scanner scanner) {
        System.out.print("Enter username to update: ");
        String username = scanner.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE users SET password = ? WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.executeUpdate();
            System.out.println("Profile updated successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private static void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO books (title, author, isbn) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, isbn);
            stmt.executeUpdate();
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void deleteBook(Scanner scanner) {
        System.out.print("Enter book ID to delete: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    private static void searchBook(Scanner scanner) {
        System.out.print("Enter book title or author: ");
        String query = scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "%" + query + "%");
            stmt.setString(2, "%" + query + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                System.out.println("Book ID: " + rs.getInt("id") + ", Title: " + rs.getString("title") + ", Author: " + rs.getString("author"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void issueBook(Scanner scanner) {
        System.out.print("Enter book ID to issue: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE books SET issued = true WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book issued successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void returnBook(Scanner scanner) {
        System.out.print("Enter book ID to return: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE books SET issued = false WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book returned successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void reserveBook(Scanner scanner) {
        System.out.print("Enter book ID to reserve: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE books SET reserved = true WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, bookId);
            stmt.executeUpdate();
            System.out.println("Book reserved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    private static void calculateFine(Scanner scanner) {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT DATEDIFF(CURDATE(), return_date) AS overdue_days FROM issued_books WHERE user_id = ? AND return_date < CURDATE()";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            
            int totalFine = 0;
            while (rs.next()) {
                int overdueDays = rs.getInt("overdue_days");
                if (overdueDays > 0) {
                    totalFine += overdueDays * 5; // Assuming Rs.5 fine per day
                }
            }
            System.out.println("Total fine: Rs." + totalFine);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } 
}
