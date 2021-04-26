package com.jadehelena.banking.service

import com.jadehelena.banking.controller.exception.PersonHasActiveAccountException
import com.jadehelena.banking.model.Account
import com.jadehelena.banking.model.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import static org.assertj.core.api.Assertions.assertThat

@SpringBootTest
class AccountServiceTest {
    @Autowired
    PersonService personService

    @Autowired
    AccountService accountService

    @Test
    void givenCreatingAccount_whenHolderAlreadyHasAccount_thenItShouldRaiseError() {
        Person person = new Person("Lucas", "Nogueira", "83245678932")
        personService.save(person)

        Account account = new Account(person)
        accountService.save(account)

        Assertions.assertThrows(PersonHasActiveAccountException.class, { ->
            accountService.validatesIfHolderDoesntHaveAccount(person.getId())
        })
    }

    @Test
    void givenCreatingAccount_whenHolderDoesntHasAccount_thenItShouldReturnFalse() {
        Person person = new Person("Nate", "Test", "9321457892")
        personService.save(person)

        assertThat(accountService.validatesIfHolderDoesntHaveAccount(person.getId())).isEqualTo(true)
    }

    @Test
    void whenGeneratingRandomNumbers_thenItShouldReturnPositiveNumbers() {
        int randomAccountNumber = accountService.generateAccountNumber()

        assertThat(randomAccountNumber).isPositive()
    }
}
