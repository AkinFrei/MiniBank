package com.example.MiniBank.Dto;

import com.example.MiniBank.Enum.Status;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TransactionDto {

    private UUID id;

    private AccountDto from;

    private AccountDto to;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    private Status status;

}
