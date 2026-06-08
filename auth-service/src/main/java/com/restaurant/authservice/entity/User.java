package com.restaurant.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(length = 50)
    private String username;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(columnDefinition = "TEXT")
    private String note;

    @Column(nullable = false)
    private Integer status;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserContact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<UserRole> userRoles = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<RefreshToken> refreshTokens = new ArrayList<>();

    // ==================== Constructors ====================

    public User(String fullName, String address, String username, String password, String note, Integer status) {
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.note = note;
        this.status = status;
    }

    // Helper methods
    public void addContact(UserContact contact) {
        contacts.add(contact);
        contact.setUser(this);
    }

    public void removeContact(UserContact contact) {
        contacts.remove(contact);
        contact.setUser(null);
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", username='" + username + '\'' +
                ", note='" + note + '\'' +
                ", status=" + status +
                '}';
    }
}
