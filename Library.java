import java.util.ArrayList;

// Library class - this does all the actual work (add book, issue, return etc)
// Main.java just calls these methods, it doesn't do any logic itself

public class Library {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<Member> members = new ArrayList<>();

    public void addBook(Book b) {
        books.add(b);
    }

    public void registerMember(Member m) {
        members.add(m);
    }

    // returns the matching books so Main can print them
    public ArrayList<Book> searchBook(String keyword) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book b : books) {
            if (b.isbn.equalsIgnoreCase(keyword) || b.title.toLowerCase().contains(keyword.toLowerCase())) {
                results.add(b);
            }
        }
        return results;
    }

    private Book findBook(String isbn) {
        for (Book b : books) {
            if (b.isbn.equals(isbn)) return b;
        }
        return null;
    }

    private Member findMember(String memberId) {
        for (Member m : members) {
            if (m.memberId.equals(memberId)) return m;
        }
        return null;
    }

    // Instead of just printing an error and returning like Version A did,
    // this throws a message that Main can catch and display.
    public void issueBook(String isbn, String memberId) throws Exception {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null) {
            throw new Exception("Book not found.");
        }
        if (member == null) {
            throw new Exception("Member not found.");
        }
        if (!book.isAvailable()) {
            throw new Exception("No copies available for \"" + book.title + "\".");
        }
        if (!member.canBorrowMore()) {
            throw new Exception(member.name + " has already borrowed the maximum of " + Member.MAX_BOOKS + " books.");
        }

        book.issueCopy();
        member.addBorrowed(isbn);
    }

    public void returnBook(String isbn, String memberId) throws Exception {
        Book book = findBook(isbn);
        Member member = findMember(memberId);

        if (book == null || member == null) {
            throw new Exception("Invalid ISBN or Member ID.");
        }

        book.returnCopy();
        member.removeBorrowed(isbn);
    }
}
