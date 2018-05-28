package sd.catalog.model;

public enum UserRole {

    ADMIN("ADMIN"),
    SELLER("SELLER");

    private String role;

    UserRole(String role) { this.role = role; }

    @Override
    public String toString() { return role; }
}
