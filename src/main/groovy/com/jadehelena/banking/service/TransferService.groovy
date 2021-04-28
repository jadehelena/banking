package com.jadehelena.banking.service

import com.jadehelena.banking.model.Account
import com.jadehelena.banking.model.Transaction
import com.jadehelena.banking.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransferService implements TransactionImpl {
    @Autowired
    AccountService accountService

    @Autowired
    TransactionRepository transactionRepository

    @Override
    def validatesValue(Transaction transaction) {
        Account originAccount = accountService.findById(transaction.originAccount.getId())

        if(originAccount.getBalance() < transaction.getValue()) {
            throw new IllegalArgumentException("Insufficient balance for this transaction")
        }
    }

    @Override
    def transact(Transaction transaction) {
        transactionRepository.save(transaction)

        accountService.updateBalance(transaction.value * -1, transaction.originAccount.getId())
        accountService.updateBalance(transaction.value, transaction.targetAccount.getId())
    }
}
