package model;

import java.time.LocalDate;
import java.util.UUID;

public class LoanRequest {
    private UUID id;
    private UUID studentId;
    private UUID bookId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LoanRequestStatus status;

    public LoanRequest(UUID studentId, UUID bookId, LocalDate start, LocalDate end) {
        this.id = UUID.randomUUID();
        this.studentId = studentId;
        this.bookId = bookId;
        this.startDate = start;
        this.endDate = end;
        this.status = LoanRequestStatus.PENDING;
    }

    public UUID getId() { return id; }
    public UUID getStudentId() { return studentId; }
    public UUID getBookId() { return bookId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public LoanRequestStatus getStatus() { return status; }

    public void setStatus(LoanRequestStatus status) { this.status = status; }
}
