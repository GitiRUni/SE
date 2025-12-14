package model;

public class Employee extends User {
    private String fullName;

    public Employee(String username, String password, String fullName) {
        super(username, password, Role.EMPLOYEE);
        this.fullName = fullName;
    }

    public String getFullName() { return fullName; }
}
