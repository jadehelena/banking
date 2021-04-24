package com.jadehelena.banking.controller

import com.jadehelena.banking.model.Transaction
import com.jadehelena.banking.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

import javax.transaction.Transactional

@RestController
@RequestMapping('transactions')
class TransactionController {
    @Autowired
    TransactionService transactionService

    @GetMapping('{id}')
    Transaction findOne(@PathVariable long id) {
        transactionService.findById(id)
    }

    @PostMapping
    @Transactional
    ResponseEntity<Transaction> save(@RequestBody Transaction transaction, UriComponentsBuilder uriBuilder) {
        try{
            transactionService.save(transaction)

            URI uri = uriBuilder.path("/transactions/{id}").buildAndExpand(transaction.getId()).toUri()
            return ResponseEntity.created(uri).body(transaction)
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.message)
        }
    }
}
