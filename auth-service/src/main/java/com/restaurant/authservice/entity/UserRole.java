package com.restaurant.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(columnDefinition = "TEXT")
    private String note;

    // ==================== Constructors ====================

    public UserRole(User user, Role role, String note) {
        this.user = user;
        this.role = role;
        this.note = note;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "UserRole{" +
                "id=" + id +
                ", userId=" + (user != null ? user.getUserId() : null) +
                ", roleId=" + (role != null ? role.getRoleId() : null) +
                ", name='" + note + '\'' +
                '}';
    }
}
