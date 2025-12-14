package service;

import model.Student;
import org.junit.jupiter.api.Test;
import repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void registerStudent_shouldCreateActiveStudent() {
        UserRepository repo = new UserRepository();
        AuthService authService = new AuthService(repo);

        Student s = authService.registerStudent("ali", "1234", "Ali Ahmadi");

        assertNotNull(s);
        assertTrue(s.isActive());
    }

    @Test
    void login_withCorrectCredentials_shouldSucceed() {
        UserRepository repo = new UserRepository();
        AuthService authService = new AuthService(repo);

        authService.registerStudent("reza", "pass", "Reza Karimi");
        assertDoesNotThrow(() -> authService.login("reza", "pass"));
    }
}
