package repository;

import model.Book;
import java.util.*;

public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public void add(Book b) { books.add(b); }

    public List<Book> getAll() { return books; }

    public List<Book> search(String title, String author, Integer year) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            boolean match = true;
            if (title != null && !b.getTitle().toLowerCase().contains(title.toLowerCase())) match = false;
            if (author != null && !b.getAuthor().toLowerCase().contains(author.toLowerCase())) match = false;
            if (year != null && b.getPublishYear() != year) match = false;
            if (match) result.add(b);
        }
        return result;
    }

    public Optional<Book> findById(UUID id) {
        return books.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
}
