package com.restaurant.sharemodules.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

/**
 * Basic user information DTO shared across services.
 * Used for inter-service communication (e.g., auth-service exposing user info
 * to order-service or menu-service).
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDTO {

    private Long userId;
    private String fullName;
    private String username;
    private String address;
    private List<String> roles;
    private Integer status;

    // ==================== toString ====================

    @Override
    public String toString() {
        return "UserInfoDTO{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", status=" + status +
                '}';
    }
}
