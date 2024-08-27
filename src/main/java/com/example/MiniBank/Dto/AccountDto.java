package com.example.MiniBank.Dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class AccountDto {

    private UUID id;

    private String name;

    private String number;

    private BigDecimal balance;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
