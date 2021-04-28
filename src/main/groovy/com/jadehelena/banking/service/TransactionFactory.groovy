package com.jadehelena.banking.service

import com.jadehelena.banking.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TransactionFactory {

    @Autowired
    DepositService depositService

    @Autowired
    TransferService transferService

    def getService(Transaction transaction){
        switch(transaction.type) {
            case "DEPOSIT":
                return depositService
            case "TRANSFER":
                return transferService
            default:
                throw new IllegalArgumentException("Transact type not configured yet")
        }
    }

}
