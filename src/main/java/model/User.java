package model;

import java.util.UUID;

public class User {
    protected UUID id;
    protected String username;
    protected String password;
    protected Role role;
    protected boolean active;

    public User(String username, String password, Role role) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = true;
    }

    public UUID getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
    public boolean isActive() { return active; }

    public void setPassword(String password) { this.password = password; }
    public void setActive(boolean active) { this.active = active; }
}
