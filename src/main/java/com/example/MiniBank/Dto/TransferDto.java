package com.example.MiniBank.Dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferDto {
    String sourceAccountName;
    String destinationAccountName;
    BigDecimal amount;
}
