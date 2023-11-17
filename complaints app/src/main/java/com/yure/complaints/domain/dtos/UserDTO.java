package com.yure.complaints.domain.dtos;

import com.yure.complaints.domain.models.Roles;
import com.yure.complaints.domain.models.User;

public record UserDTO(
        Long id,
        String name,
        String email,
        Roles role
) {
    public static UserDTO fromUser(User user){
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole());
    }
}
