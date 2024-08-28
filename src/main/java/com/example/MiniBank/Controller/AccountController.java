package com.example.MiniBank.Controller;

import com.example.MiniBank.Dto.AccountDto;
import com.example.MiniBank.Service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto){
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountDetails(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> searchAccounts(@RequestParam String searchText){
        return ResponseEntity.ok(accountService.searchAccounts(searchText));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id, @RequestParam AccountDto accountDto){
        return ResponseEntity.ok(accountService.updateAccount(id, accountDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<AccountDto>> deleteAccount(@PathVariable String id){
        return ResponseEntity.ok(accountService.deleteAccount(id));
    }

}
