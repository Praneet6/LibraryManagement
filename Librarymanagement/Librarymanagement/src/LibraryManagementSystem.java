import java.util.*;

public class LibraryManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static LibraryDB db = new LibraryDB();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. View All Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    db.viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    db.closeConnection();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Book Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        db.addBook(id, title, author);
    }

    private static void issueBook() {
        System.out.print("Enter Book ID to issue: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Borrower Name: ");
        String borrower = sc.nextLine();
        db.issueBook(id, borrower);
    }

    private static void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = sc.nextInt();
        sc.nextLine();
        db.returnBook(id);
    }
}

