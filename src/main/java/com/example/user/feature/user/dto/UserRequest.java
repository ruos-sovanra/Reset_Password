package com.example.user.feature.user.dto;

import com.example.user.feature.password.FieldMatch;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
@FieldMatch(first = "password", second = "confirm_password", message = "The password fields must match")
public record UserRequest(
        String first_name,
        String last_name,
        String username,
        String email,
        String password,
        String confirm_password,
        String phone,
        String avatar

) {
}
