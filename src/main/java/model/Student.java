package model;

public class Student extends User {
    private String fullName;

    public Student(String username, String password, String fullName) {
        super(username, password, Role.STUDENT);
        this.fullName = fullName;
    }

    public String getFullName() { return fullName; }
}
