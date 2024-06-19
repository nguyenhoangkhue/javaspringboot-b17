package com.example.javaspringbootb17.model.request;

import com.example.javaspringbootb17.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateUserRequest {
    @NotEmpty(message="Tên không được để trống")
    String name;
    @NotEmpty(message="Email không được để trống")
    @Email(message="Email không hợp lệ")
    String email;
    @NotNull(message="Role không được để trống")
    Role role;
}
