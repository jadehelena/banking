package com.jadehelena.banking.repository;

import com.jadehelena.banking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>  {
    
}
