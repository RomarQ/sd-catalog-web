import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * User JPA Entity
 */

@Entity
@Table(name = "Users")
@NamedQueries(
    {
        @NamedQuery (
            name = "User.findUserByEmail",
            query = "select u from User u where u.email = :email"
        ),
        @NamedQuery (
            name = "User.findUserById",
            query = "select u from User u where u.userId = :id"
        )
    }
)
public class User implements Serializable {

    public static final String FIND_BY_EMAIL    = "User.findUserByEmail";
    public static final String FIND_BY_ID       = "User.findUserById";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_Id")
    private int userId;

    @Column(name = "email", length = 64, unique = true)
    private String email;

    @Column(name = "hashedPassword", length = 24)
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "createdAt")
    private DateTime createdAt;

    public int getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public DateTime getCreationDate() {
        return createdAt;
    }
}
