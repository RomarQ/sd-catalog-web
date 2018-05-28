package sd.catalog.model;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(name = "email")
    @Email
    private String email;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "phone")
    @Pattern(regexp = "^[0-9+]*$")
    private String phone;

    @NotNull
    @Column(name = "password")
    @Convert(converter = PasswordEncryptConverter.class)
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role = UserRole.SELLER;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    private Date createdAt;


    @PrePersist
    protected void onCreate() {
        createdAt = new Date();
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public UserRole getRole() { return role; }

    public void setRole(UserRole role) { this.role = role; }

    public Date getCreatedAt() { return createdAt; }

    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }


    @Override
    public boolean equals(Object obj) {
        return obj instanceof User && Objects.equals(id, ((User) obj).id);
    }
}
