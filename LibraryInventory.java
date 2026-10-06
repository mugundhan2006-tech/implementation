import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Keeps the in-memory book collection and provides its CRUD operations.
 */
public final class LibraryInventory {
    // A map gives direct ISBN lookups while preserving the order books were added.
    private final Map<String, Book> booksByIsbn = new LinkedHashMap<>();

    /** Adds a book unless another book already uses its ISBN. */
    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if (booksByIsbn.containsKey(book.getIsbn())) {
            throw new IllegalArgumentException("A book with that ISBN already exists.");
        }
        booksByIsbn.put(book.getIsbn(), book);
    }

    /** Returns a read-only snapshot of all books, in insertion order. */
    public List<Book> getAllBooks() {
        return Collections.unmodifiableList(new ArrayList<>(booksByIsbn.values()));
    }

    /** Finds one book by its ISBN, or returns null if it is not present. */
    public Book findBook(String isbn) {
        return booksByIsbn.get(normalizeIsbn(isbn));
    }

    /** Replaces a book's details and supports changing its ISBN safely. */
    public void updateBook(String currentIsbn, Book updatedBook) {
        String normalizedCurrentIsbn = normalizeIsbn(currentIsbn);
        if (!booksByIsbn.containsKey(normalizedCurrentIsbn)) {
            throw new IllegalArgumentException("No book was found with that ISBN.");
        }
        if (updatedBook == null) {
            throw new IllegalArgumentException("Updated book cannot be null.");
        }
        if (!normalizedCurrentIsbn.equals(updatedBook.getIsbn())
                && booksByIsbn.containsKey(updatedBook.getIsbn())) {
            throw new IllegalArgumentException("Another book already uses the new ISBN.");
        }

        // Remove the old key only after all validation succeeds.
        booksByIsbn.remove(normalizedCurrentIsbn);
        booksByIsbn.put(updatedBook.getIsbn(), updatedBook);
    }

    /** Deletes the book with the supplied ISBN. */
    public boolean deleteBook(String isbn) {
        return booksByIsbn.remove(normalizeIsbn(isbn)) != null;
    }

    private String normalizeIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty.");
        }
        return isbn.trim();
    }
}