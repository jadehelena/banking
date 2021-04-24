package com.jadehelena.banking.repository

import com.jadehelena.banking.model.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository extends JpaRepository<Account, Long>  {
    Account findByHolderId(Long id)
}
