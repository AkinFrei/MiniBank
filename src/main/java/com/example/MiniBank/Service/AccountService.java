package com.example.MiniBank.Service;

import com.example.MiniBank.Dto.AccountDto;
import com.example.MiniBank.Entity.Account;
import com.example.MiniBank.Entity.User;
import com.example.MiniBank.Repository.AccountRepository;
import com.example.MiniBank.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    public AccountDto createAccount(AccountDto accountDto) {
        return modelMapper.map(accountRepository.save(modelMapper.map(accountDto, Account.class)), AccountDto.class);
    }

    public AccountDto updateAccount(String id, AccountDto accountDto) {
        Account account = accountRepository.findById(UUID.fromString(id)).
                orElseThrow(() -> new RuntimeException("Account not found"));
        modelMapper.map(accountDto, account);
        return modelMapper.map(accountRepository.save(modelMapper.map(accountDto, Account.class)), AccountDto.class);
    }

    public List<AccountDto> searchAccounts(String searchString) {
        return accountRepository.findBySearchString(searchString ,getCurrentUserId())
                .stream().map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
    }


    public UUID getCurrentUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
        String username = authentication.getName(); // Get username (or email) from authentication
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId(); // Return user ID
    }
        throw new RuntimeException("User is not authenticated");
}

    public List<AccountDto> getAccountsForCurrentUser() {
        return accountRepository.findAllByOwnerId(getCurrentUserId())
                .stream().map(account -> modelMapper.map(account, AccountDto.class)).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id) {
        return modelMapper.map(accountRepository.findAccountByOwnerIdAndId(getCurrentUserId(), UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Invalid Acoount id")), AccountDto.class);
    }

    public List<AccountDto> deleteAccount(String id) {
        accountRepository.deleteById(UUID.fromString(id));
        return getAccountsForCurrentUser();
    }


}
