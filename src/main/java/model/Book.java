package model;

import java.util.UUID;

public class Book {
    private UUID id;
    private String title;
    private String author;
    private int publishYear;
    private boolean available;

    public Book(String title, String author, int publishYear) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
        this.available = true;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublishYear() { return publishYear; }
    public boolean isAvailable() { return available; }

    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return title + " - " + author + " (" + publishYear + ")" +
                (available ? " ✅ موجود" : " ❌ امانت داده شده");
    }
}
