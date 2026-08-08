import java.util.ArrayList;
import java.util.Scanner;

// Main class - just handles the menu and user input.
// All the actual logic lives in Library.java now instead of one big file.

public class Main {
    static Library library = new Library();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        // a couple of sample records so the demo has something to search/issue
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", 3));
        library.addBook(new Book("978-0596009205", "Head First Design Patterns", "Freeman & Robson", 2));
        library.registerMember(new Member("M001", "Thanuja Jayawardena"));

        int choice;
        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM (AI-Assisted) =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 1) {
                    addBook();
                } else if (choice == 2) {
                    registerMember();
                } else if (choice == 3) {
                    searchBook();
                } else if (choice == 4) {
                    issueBook();
                } else if (choice == 5) {
                    returnBook();
                } else if (choice == 6) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                // issueBook/returnBook throw an Exception with a readable message,
                // so we just print it here instead of crashing
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 6);

        sc.close();
    }

    static void addBook() {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Title: ");
        String title = sc.nextLine();
        System.out.print("Author: ");
        String author = sc.nextLine();
        System.out.print("Copies: ");
        int copies = sc.nextInt();
        sc.nextLine();

        library.addBook(new Book(isbn, title, author, copies));
        System.out.println("Book added successfully.");
    }

    static void registerMember() {
        System.out.print("Member ID: ");
        String id = sc.nextLine();
        System.out.print("Name: ");
        String name = sc.nextLine();

        library.registerMember(new Member(id, name));
        System.out.println("Member registered successfully.");
    }

    static void searchBook() {
        System.out.print("Enter ISBN or title keyword: ");
        String keyword = sc.nextLine();
        ArrayList<Book> results = library.searchBook(keyword);

        if (results.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            for (Book b : results) {
                System.out.println(b);
            }
        }
    }

    static void issueBook() throws Exception {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Member ID: ");
        String memberId = sc.nextLine();

        library.issueBook(isbn, memberId);
        System.out.println("Book issued successfully.");
    }

    static void returnBook() throws Exception {
        System.out.print("ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Member ID: ");
        String memberId = sc.nextLine();

        library.returnBook(isbn, memberId);
        System.out.println("Book returned successfully.");
    }
}
