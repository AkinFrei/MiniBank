package com.example.MiniBank.Service;

import com.example.MiniBank.Dto.TransactionDto;
import com.example.MiniBank.Dto.TransferDto;
import com.example.MiniBank.Entity.Account;
import com.example.MiniBank.Entity.Transaction;
import com.example.MiniBank.Enum.Status;
import com.example.MiniBank.Repository.AccountRepository;
import com.example.MiniBank.Repository.TransactionsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionsRepository transactionsRepository;
    private final AccountRepository accountRepository;
    private final AccountService accountService;
    ModelMapper modelMapper = new ModelMapper();


    public List<TransactionDto> transferMoney(TransferDto transferDto) {
        Account sourceAccount = accountRepository.findAccountByName(transferDto.getSourceAccountName()).
                orElseThrow(() -> new RuntimeException("Account not found for name: " + transferDto.getSourceAccountName()));

        Account destiantionAccount = accountRepository.findAccountByName(transferDto.getDestinationAccountName()).
                orElseThrow(() -> new RuntimeException("Account not found name: " + transferDto.getDestinationAccountName()));
        
        if (!sourceAccount.getOwner().getId().equals(accountService.getCurrentUserId())){
            throw new RuntimeException("Only account owner can make a transfer request");
        }
        else if (transferDto.getAmount().compareTo(sourceAccount.getBalance()) < 0) {
            throw new RuntimeException("Insufficient balance. Please reduce the transfer amount to match your available funds.");
        }

        sourceAccount.setBalance(sourceAccount.getBalance().subtract(transferDto.getAmount()));
        destiantionAccount.setBalance(destiantionAccount.getBalance().add(transferDto.getAmount()));

        transactionsRepository.save(Transaction.
                builder().
                transactionDate(LocalDateTime.now()).
                from(sourceAccount).
                to(destiantionAccount).
                status(Status.SUCCESS).
                amount(transferDto.getAmount()).
                build());

        return transactionsRepository.findAllByFromOrTo(accountService.getCurrentUserId())
                .stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());

    }
    

    public List<TransactionDto> getTransactionHistoryByAccountId(String accountId) {
        return transactionsRepository.findAllByFromOrTo(UUID.fromString(accountId))
                .stream().map(transaction -> modelMapper.map(transaction, TransactionDto.class)).collect(Collectors.toList());
    }


}
