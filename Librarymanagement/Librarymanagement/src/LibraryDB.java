import java.sql.*;

public class LibraryDB {
    private Connection con;
    private PreparedStatement pst;
    private ResultSet rs;

    public LibraryDB() {
        try {
            // Load MySQL Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to your database
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/librarydb",  // Database URL
                "root",                                   // Username
                "Garima@2021"                            // Replace with your MySQL password
            );

            System.out.println("✅ Database connected successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error connecting to database: " + e.getMessage());
        }
    }

    public void addBook(int id, String title, String author) {
        try {
            String query = "INSERT INTO books (book_id, title, author, status) VALUES (?, ?, ?, 'Available')";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, title);
            pst.setString(3, author);
            pst.executeUpdate();
            System.out.println("📚 Book added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding book: " + e.getMessage());
        }
    }

    public void viewBooks() {
        try {
            String query = "SELECT * FROM books";
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();

            System.out.println("\n--- BOOK LIST ---");
            System.out.printf("%-10s %-30s %-20s %-15s %-20s\n", "ID", "Title", "Author", "Status", "Borrower");

            while (rs.next()) {
                System.out.printf("%-10d %-30s %-20s %-15s %-20s\n",
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("status"),
                        rs.getString("borrower_name"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing books: " + e.getMessage());
        }
    }

    public void issueBook(int id, String borrower) {
        try {
            String check = "SELECT status FROM books WHERE book_id = ?";
            pst = con.prepareStatement(check);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if (rs.next()) {
                if (rs.getString("status").equalsIgnoreCase("Available")) {
                    String query = "UPDATE books SET status = 'Issued', borrower_name = ? WHERE book_id = ?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, borrower);
                    pst.setInt(2, id);
                    pst.executeUpdate();
                    System.out.println("✅ Book issued to " + borrower + "!");
                } else {
                    System.out.println("⚠️ Book already issued!");
                }
            } else {
                System.out.println("⚠️ Book ID not found!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error issuing book: " + e.getMessage());
        }
    }

    public void returnBook(int id) {
        try {
            String query = "UPDATE books SET status = 'Available', borrower_name = NULL WHERE book_id = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, id);
            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println("✅ Book returned successfully!");
            } else {
                System.out.println("⚠️ Book ID not found!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error returning book: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error closing connection.");
        }
    }
}

