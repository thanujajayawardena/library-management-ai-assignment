// Book class - holds the info for one book title
// (asked ChatGPT how to split my one big file into proper classes,
// this is basically what it suggested, just tidied up a bit)

public class Book {
    String isbn;
    String title;
    String author;
    int totalCopies;
    int availableCopies;

    public Book(String isbn, String title, String author, int totalCopies) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }

    public boolean isAvailable() {
        return availableCopies > 0;
    }

    public void issueCopy() {
        availableCopies--;
    }

    public void returnCopy() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }

    public String toString() {
        return isbn + " - " + title + " by " + author + " (" + availableCopies + "/" + totalCopies + " available)";
    }
}
