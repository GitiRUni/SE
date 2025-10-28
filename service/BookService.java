package service;

import model.Book;
import repository.BookRepository;
import java.util.*;

public class BookService {
    private final BookRepository repo;

    public BookService(BookRepository repo) { this.repo = repo; }

    public void addBook(String title, String author, int year) {
        repo.add(new Book(title, author, year));
    }

    public List<Book> search(String title, String author, Integer year) {
        return repo.search(title, author, year);
    }

    public List<Book> allBooks() { return repo.getAll(); }
}
