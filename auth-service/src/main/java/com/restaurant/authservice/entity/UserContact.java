package com.restaurant.authservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_contact")
public class UserContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_contact_id")
    private Long userContactId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "contact_type")
    private String contactType;

    @Column(name = "contact_value")
    private String contactValue;

    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    // ==================== Constructors ====================

    public UserContact(User user, String contactType, String contactValue, Boolean isPrimary) {
        this.user = user;
        this.contactType = contactType;
        this.contactValue = contactValue;
        this.isPrimary = isPrimary;
    }

    // ==================== toString ====================

    @Override
    public String toString() {
        return "UserContact{" +
                "userContactId=" + userContactId +
                ", userId=" + (user != null ? user.getUserId() : null) +
                ", contactType='" + contactType + '\'' +
                ", contactValue='" + contactValue + '\'' +
                ", isPrimary=" + isPrimary +
                '}';
    }
}
