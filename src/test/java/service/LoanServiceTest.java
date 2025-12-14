package service;

import model.Book;
import model.Student;
import model.LoanRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.LoanRepository;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private BookRepository bookRepo;
    private LoanRepository loanRepo;
    private LoanService loanService;
    private Student student;
    private Book book;

    @BeforeEach
    void setUp() {
        bookRepo = new BookRepository();
        loanRepo = new LoanRepository();
        loanService = new LoanService(loanRepo, bookRepo);
        student = new Student("user1", "pass", "John Doe");
        book = new Book("Java", "James Gosling", 1995);
        bookRepo.add(book);
    }

    @Test
    void requestLoan_shouldAddLoanAndMarkBookUnavailable() {
        loanService.requestLoan(student, book, LocalDate.now(), LocalDate.now().plusDays(7));

        List<LoanRequest> loans = loanService.allLoans();
        assertEquals(1, loans.size());
        assertFalse(book.isAvailable());
    }

    @Test
    void requestLoan_forInactiveStudent_shouldThrowException() {
        student.setActive(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                loanService.requestLoan(student, book, LocalDate.now(), LocalDate.now().plusDays(7))
        );
        assertEquals("دانشجو غیرفعال است!", ex.getMessage());
    }

    @Test
    void requestLoan_forUnavailableBook_shouldThrowException() {
        book.setAvailable(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                loanService.requestLoan(student, book, LocalDate.now(), LocalDate.now().plusDays(7))
        );
        assertEquals("کتاب در حال حاضر موجود نیست!", ex.getMessage());
    }
}
