package com.restaurant.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // ==================== Constructors ====================

    public User(String fullName, String address, String username, String password, String note, Integer status) {
        this.fullName = fullName;
        this.address = address;
        this.username = username;
        this.password = password;
        this.note = note;
        this.status = status;
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
