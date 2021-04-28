package com.jadehelena.banking.service

import com.jadehelena.banking.model.Transaction

interface TransactionImpl {
    def validatesValue(Transaction transaction)
    def transact(Transaction transaction)
}