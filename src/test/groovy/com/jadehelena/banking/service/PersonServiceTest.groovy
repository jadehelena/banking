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
class PersonServiceTest {

    @Autowired
    PersonService personService

    @Autowired
    AccountService accountService

    @Test
    void givenCheckingIfPersonCanBeDeleted_thenItHasAccount_thenItShouldRaiseError() {
        Person person = new Person("Jade", "Helena", "00182570207")
        personService.save(person)

        Account account = new Account(person)
        accountService.save(account)

        Assertions.assertThrows(PersonHasActiveAccountException.class, { ->
            personService.deleteById(person.getId())
        })
    }

    @Test
    void givenCheckingIfPersonCanBeDeleted_thenItDoesntHasAccount_thenItShouldBeDeleted() {
        Person person = new Person("Jade", "Helena", "00182570207")
        personService.save(person)

        personService.deleteById(person.getId())

        assertThat(personService.findById(person.getId())).isNull()
    }
}
