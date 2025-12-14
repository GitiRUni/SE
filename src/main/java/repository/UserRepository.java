package repository;

import model.*;
import java.util.*;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void add(User u) { users.add(u); }

    public Optional<User> findByUsername(String username) {
        return users.stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst();
    }

    public List<User> getAll() { return users; }

    public List<Student> getAllStudents() {
        List<Student> s = new ArrayList<>();
        for (User u : users)
            if (u instanceof Student) s.add((Student) u);
        return s;
    }
}
