package com.example.MiniBank.Entity;


import com.example.MiniBank.Enum.Status;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "from_account_id")
    private Account from;

    @ManyToOne
    @JoinColumn(name = "to_account_id")
    private Account to;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "transaction_date")
    private LocalDateTime transactionDate;

    @Column(name = "status")
    private Status status;

}
