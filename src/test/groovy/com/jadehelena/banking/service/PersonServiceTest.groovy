package com.jadehelena.banking.service

import com.jadehelena.banking.exception.PersonHasActiveAccountException
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
    void givenCheckingIfPersonCanBeDeleted_whenItHaveAccount_thenItShouldRaiseError() {
        Person person = new Person("Maria", "Joana", "92382463789")
        personService.save(person)

        Account account = new Account(person)
        accountService.save(account)

        Assertions.assertThrows(PersonHasActiveAccountException.class, { ->
            personService.deleteById(person.getId())
        })
    }

    @Test
    void givenCheckingIfPersonCanBeDeleted_whenItDoesntHaveAccount_thenItShouldBeDeleted() {
        Person person = new Person("Helena", "da Silva", "82457629012")
        personService.save(person)

        personService.deleteById(person.getId())

        assertThat(personService.findById(person.getId())).isNull()
    }
}
