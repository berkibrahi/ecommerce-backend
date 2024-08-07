package com.ecommerce.springJwt.dto;

import com.ecommerce.springJwt.model.Role;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private Role role;

}
