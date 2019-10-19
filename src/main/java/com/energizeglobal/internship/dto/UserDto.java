package com.energizeglobal.internship.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserDto {

    private Long id;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;
    @NotEmpty
    @Email
    private String email;

    @Size(min = 6, max = 26)
    private String password;
}
