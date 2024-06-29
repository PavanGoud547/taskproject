package entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="password", nullable = false)
    private String password;

}
