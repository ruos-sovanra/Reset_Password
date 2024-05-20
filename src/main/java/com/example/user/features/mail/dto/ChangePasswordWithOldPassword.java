package com.example.user.features.mail.dto;

import lombok.Builder;

@Builder
public record ChangePasswordWithOldPassword(
        String oldPassword,
        String newPassword,
        String confirmPassword
) {
}
