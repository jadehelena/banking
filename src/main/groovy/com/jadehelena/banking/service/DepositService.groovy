package com.jadehelena.banking.service

import com.jadehelena.banking.model.Transaction
import com.jadehelena.banking.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DepositService implements TransactionImpl{
    @Autowired
    TransactionRepository transactionRepository

    @Autowired
    AccountService accountService

    @Override
    def validatesValue(Transaction transaction) {
        if(transaction.value > 2000){
            throw new IllegalArgumentException("Above deposit limit")
        }
    }

    @Override
    def transact(Transaction transaction) {
        transactionRepository.save(transaction)
        accountService.updateBalance(transaction.value, transaction.targetAccount.getId())
    }
}
