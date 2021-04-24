package com.jadehelena.banking.controller

import com.jadehelena.banking.controller.dto.AccountDto
import com.jadehelena.banking.model.Account
import com.jadehelena.banking.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder

import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional

@RestController
@RequestMapping('accounts')
class AccountController {

    @Autowired
    AccountService accountService

    @GetMapping('{id}')
    ResponseEntity<AccountDto> findOne(@PathVariable long id) {
        try{
            Account account = accountService.findByIdOrError(id)
            return ResponseEntity.ok().body(new AccountDto(account))
        } catch (EntityNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.message)
        }
    }

    @PostMapping
    @Transactional
    ResponseEntity<AccountDto> save(@RequestBody Account account, UriComponentsBuilder uriBuilder) {
        try{
            accountService.save(account)

            URI uri = uriBuilder.path("/accounts/{id}").buildAndExpand(account.getId()).toUri()
            return ResponseEntity.created(uri).body(new AccountDto(account))
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.message)
        }
    }
}
