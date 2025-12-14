package repository;

import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {

    private BookRepository bookRepo;

    @BeforeEach
    void setUp() {
        bookRepo = new BookRepository();
    }

    @Test
    void addBook_shouldIncreaseSize() {
        Book book = new Book("Clean Code", "Robert Martin", 2008);
        bookRepo.add(book);

        assertEquals(1, bookRepo.getAll().size());
    }

    @Test
    void searchByTitle_shouldReturnCorrectBook() {
        Book book = new Book("Java", "James Gosling", 1995);
        bookRepo.add(book);

        List<Book> result = bookRepo.search("Java", null, null);
        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

    @Test
    void findById_shouldReturnBook() {
        Book book = new Book("Python", "Guido van Rossum", 2000);
        bookRepo.add(book);

        UUID id = book.getId();
        assertTrue(bookRepo.findById(id).isPresent());
        assertEquals("Python", bookRepo.findById(id).get().getTitle());
    }
}
