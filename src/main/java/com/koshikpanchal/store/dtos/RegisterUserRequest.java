package com.koshikpanchal.store.dtos;

import com.koshikpanchal.store.validation.Lowercase;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank(message = "name is required")
    @Size(max = 255)
    private String name;

    @NotBlank
    @Email
    @Lowercase
    private String email;

    @NotBlank
    @Size(min = 6, max = 25)
    private String password;

}
