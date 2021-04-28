package com.jadehelena.banking.service


import com.jadehelena.banking.model.Transaction
import com.jadehelena.banking.repository.TransactionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TransactionService {
    @Autowired
    TransactionRepository transactionRepository

    @Autowired
    TransactionFactory transactionFactory

    Transaction findById(long id) {
        transactionRepository.findById(id).orElse(null)
    }

    def save(Transaction transaction) {
        def transactTypeService = transactionFactory.getService(transaction)
        transactTypeService.validatesValue(transaction)
        transactTypeService.transact(transaction)
    }
}
