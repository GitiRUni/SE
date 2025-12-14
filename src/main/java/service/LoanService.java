package service;

import model.*;
import repository.*;

import java.time.LocalDate;
import java.util.*;

public class LoanService {
    private final LoanRepository loanRepo;
    private final BookRepository bookRepo;

    public LoanService(LoanRepository loanRepo, BookRepository bookRepo) {
        this.loanRepo = loanRepo;
        this.bookRepo = bookRepo;
    }

    public void requestLoan(Student student, Book book, LocalDate start, LocalDate end) {
        if (!student.isActive()) throw new RuntimeException("دانشجو غیرفعال است!");
        if (!book.isAvailable()) throw new RuntimeException("کتاب در حال حاضر موجود نیست!");
        LoanRequest req = new LoanRequest(student.getId(), book.getId(), start, end);
        loanRepo.add(req);
        book.setAvailable(false);
    }

    public List<LoanRequest> allLoans() { return loanRepo.getAll(); }
}
