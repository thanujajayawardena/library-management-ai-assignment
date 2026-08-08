import java.util.ArrayList;
import java.util.Scanner;

// Simple Library Management System - written without any AI help
// Uses basic arrays/lists to store book and member info

public class LibrarySystem {

    static ArrayList<String> isbnList = new ArrayList<>();
    static ArrayList<String> titleList = new ArrayList<>();
    static ArrayList<Integer> copiesList = new ArrayList<>();

    static ArrayList<String> memberIdList = new ArrayList<>();
    static ArrayList<String> memberNameList = new ArrayList<>();
    static ArrayList<Integer> borrowedCountList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Search Book");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                addBook(sc);
            } else if (choice == 2) {
                registerMember(sc);
            } else if (choice == 3) {
                searchBook(sc);
            } else if (choice == 4) {
                issueBook(sc);
            } else if (choice == 5) {
                returnBook(sc);
            } else if (choice == 6) {
                System.out.println("Exiting system.");
            } else {
                System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        sc.close();
    }

    static void addBook(Scanner sc) {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Copies: ");
        int copies = sc.nextInt();
        sc.nextLine();

        isbnList.add(isbn);
        titleList.add(title);
        copiesList.add(copies);
        System.out.println("Book added successfully.");
    }

    static void registerMember(Scanner sc) {
        System.out.print("Enter Member ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        memberIdList.add(id);
        memberNameList.add(name);
        borrowedCountList.add(0);
        System.out.println("Member registered.");
    }

    static void searchBook(Scanner sc) {
        System.out.print("Enter ISBN or title keyword: ");
        String key = sc.nextLine();
        boolean found = false;
        for (int i = 0; i < isbnList.size(); i++) {
            if (isbnList.get(i).equals(key) || titleList.get(i).contains(key)) {
                System.out.println(isbnList.get(i) + " - " + titleList.get(i) +
                        " (" + copiesList.get(i) + " copies)");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No matching book found.");
        }
    }

    static void issueBook(Scanner sc) {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();

        int bookIndex = isbnList.indexOf(isbn);
        int memberIndex = memberIdList.indexOf(memberId);

        if (bookIndex == -1) {
            System.out.println("Book not found.");
            return;
        }
        if (memberIndex == -1) {
            System.out.println("Member not found.");
            return;
        }
        if (copiesList.get(bookIndex) <= 0) {
            System.out.println("No copies available.");
            return;
        }
        if (borrowedCountList.get(memberIndex) >= 3) {
            System.out.println("Borrow limit reached (max 3 books).");
            return;
        }

        copiesList.set(bookIndex, copiesList.get(bookIndex) - 1);
        borrowedCountList.set(memberIndex, borrowedCountList.get(memberIndex) + 1);
        System.out.println("Book issued successfully.");
    }

    static void returnBook(Scanner sc) {
        System.out.print("Enter ISBN: ");
        String isbn = sc.nextLine();
        System.out.print("Enter Member ID: ");
        String memberId = sc.nextLine();

        int bookIndex = isbnList.indexOf(isbn);
        int memberIndex = memberIdList.indexOf(memberId);

        if (bookIndex == -1 || memberIndex == -1) {
            System.out.println("Invalid ISBN or Member ID.");
            return;
        }

        copiesList.set(bookIndex, copiesList.get(bookIndex) + 1);
        borrowedCountList.set(memberIndex, borrowedCountList.get(memberIndex) - 1);
        System.out.println("Book returned successfully.");
    }
}
