package service;

import model.*;
import repository.UserRepository;

public class AuthService {
    private final UserRepository repo;

    public AuthService(UserRepository repo) {
        this.repo = repo;
    }

    public Student registerStudent(String username, String password, String fullName) {
        if (repo.findByUsername(username).isPresent())
            throw new RuntimeException("نام کاربری تکراری است!");
        Student s = new Student(username, password, fullName);
        repo.add(s);
        return s;
    }

    public User login(String username, String password) {
        return repo.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("نام کاربری یا رمز اشتباه است"));
    }
}
