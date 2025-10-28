package repository;

import model.LoanRequest;
import java.util.*;

public class LoanRepository {
    private final List<LoanRequest> loans = new ArrayList<>();

    public void add(LoanRequest l) { loans.add(l); }
    public List<LoanRequest> getAll() { return loans; }
}
