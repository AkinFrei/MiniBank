package com.example.MiniBank.Controller;


import com.example.MiniBank.Dto.TransactionDto;
import com.example.MiniBank.Dto.TransferDto;
import com.example.MiniBank.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/account/{account_id}")
    public ResponseEntity<List<TransactionDto>> getTransactionHistoryByAccountId(@PathVariable String account_id) {
        return ResponseEntity.ok(transactionService.getTransactionHistoryByAccountId(account_id));
    }

    @PostMapping("/transfer")
    public ResponseEntity<List<TransactionDto>> transferMoney(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok(transactionService.transferMoney(transferDto));
    }
}
