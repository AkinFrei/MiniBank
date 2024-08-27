package com.example.MiniBank.Repository;

import com.example.MiniBank.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query("select account from Account account where account.owner.id = :userId " +
            "and (account.name like %:searchString% or account.number like %:searchString%)")
    List<Account> findBySearchString(@Param("searchString") String searchString,
                                      @Param("userId") UUID id);

    List<Account> findAllByOwnerId(UUID userId);

    Optional<Account> findAccountByName(String name);

    Optional<Account> findAccountByOwnerIdAndId(UUID userId, UUID id);


}
