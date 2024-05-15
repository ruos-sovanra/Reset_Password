package com.example.user.feature.user.dto;

import com.example.user.feature.password.FieldMatch;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.validation.annotation.Validated;

@Builder
@Validated
@FieldMatch(first = "password", second = "confirm_password", message = "The password fields must match")
public record CreateUserRequest(
        String first_name,
        String last_name,
        String username,
        @Email(message = "Email format is not correct!")
        String email,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "Password must be at least 8 characters and include at least one uppercase letter, one lowercase letter, one number, and one special character")
        String password,
        String confirm_password,
        String phone,
        String avatar,
        String roleName,
        String AccTypeName

) {
}
