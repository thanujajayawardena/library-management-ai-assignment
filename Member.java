import java.util.ArrayList;

// Member class - keeps track of one library member
// and which books they currently have

public class Member {
    static final int MAX_BOOKS = 3;

    String memberId;
    String name;
    ArrayList<String> borrowedBooks = new ArrayList<>();

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }

    public boolean canBorrowMore() {
        return borrowedBooks.size() < MAX_BOOKS;
    }

    public void addBorrowed(String isbn) {
        borrowedBooks.add(isbn);
    }

    public void removeBorrowed(String isbn) {
        borrowedBooks.remove(isbn);
    }

    public String toString() {
        return memberId + " - " + name + " (" + borrowedBooks.size() + " book(s) borrowed)";
    }
}
