package com.jadehelena.banking.repository;

import com.jadehelena.banking.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long>  {
    
}
