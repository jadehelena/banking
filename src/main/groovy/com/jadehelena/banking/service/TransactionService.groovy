package com.jadehelena.banking.service

import com.jadehelena.banking.model.Account
import com.jadehelena.banking.model.Transaction
import com.jadehelena.banking.repository.AccountRepository
import com.jadehelena.banking.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityNotFoundException

@Service
class TransactionService {
    @Autowired
    TransactionRepository transactionRepository

    @Autowired
    AccountRepository accountRepository

    @Autowired
    AccountService accountService

    Transaction findById(long id) {
        transactionRepository.findById(id).orElse(null)
    }

    Transaction findByIdOrError(long id) {
        transactionRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    def save(Transaction transaction) {
        if(transaction.type == "DEPOSIT"){
            validatesDepositLimit(transaction)
        } else {
            validatesIfAccountHasEnoughBalance(transaction)
            accountService.updateBalance(transaction.value * -1, transaction.fromAccount.getId())
        }

        accountService.updateBalance(transaction.value, transaction.toAccount.getId())
        transactionRepository.save(transaction)
    }

    def validatesIfAccountHasEnoughBalance(Transaction transaction) {
        def account = accountService.findByIdOrError(transaction.fromAccount.getId())

        if(account.getBalance() < transaction.value){
            throw new RuntimeException("Insufficient balance for this transaction")
        }
    }

    def validatesDepositLimit(Transaction transaction) {
        if(transaction.value > 2000){
            throw new RuntimeException("Above deposit limit")
        }
    }
}
