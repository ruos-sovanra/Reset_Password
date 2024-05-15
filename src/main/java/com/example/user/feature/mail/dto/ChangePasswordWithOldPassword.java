package com.example.user.feature.mail.dto;

import lombok.Builder;

@Builder
public record ChangePasswordWithOldPassword(
        String oldPassword,
        String newPassword,
        String confirmPassword
) {
}
