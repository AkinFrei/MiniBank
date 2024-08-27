package com.example.MiniBank.Repository;

import com.example.MiniBank.Entity.Account;
import com.example.MiniBank.Entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.UUID;


@Repository
public interface TransactionsRepository extends JpaRepository<Transaction, Long> {

    @Query(value = "select transaction from Transaction transaction " +
            "where transaction.from.id =:userId or transaction.to.id =:userId")
    List<Transaction> findAllByFromOrTo(@Param("userId") UUID userId);
}
