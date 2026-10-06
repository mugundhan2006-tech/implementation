/**
 * An immutable book record stored by the library inventory.
 */
public final class Book {
    private final String title;
    private final String author;
    private final String isbn;
    private final int publicationYear;

    /**
     * Creates a book after trimming and validating its text properties.
     *
     * @param title book title
     * @param author book author
     * @param isbn unique ISBN used to identify the book
     * @param publicationYear year in which the book was published
     */
    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = requireText(title, "Title");
        this.author = requireText(author, "Author");
        this.isbn = requireText(isbn, "ISBN");
        if (publicationYear < 1 || publicationYear > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("Publication year must be between 1 and the current year.");
        }
        this.publicationYear = publicationYear;
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be empty.");
        }
        return value.trim();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    @Override
    public String toString() {
        return String.format("%-32s | %-24s | %-18s | %4d",
                title, author, isbn, publicationYear);
    }
}