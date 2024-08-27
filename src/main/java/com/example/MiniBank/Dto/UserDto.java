package com.example.MiniBank.Dto;


import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.util.Date;
import java.util.UUID;

@Data
public class UserDto {

    private UUID uuid;

    @NotNull(message = "User Name can not be null")
    private String userName;

    @NotNull(message = "Email can not be null")
    private String email;

    @NotNull(message = "Password Name can not be null")
    private String password;

    private Date createdAt;

    private Date updatedAt;
}
