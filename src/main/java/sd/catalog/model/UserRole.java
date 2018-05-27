package sd.catalog.model;

public enum UserRole {

    ADMIN("ADMIN"),
    USER("USER");

    private String role;

    UserRole(String role) { this.role = role; }

    @Override
    public String toString() { return role; }
}
