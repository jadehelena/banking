package com.jadehelena.banking.service

import com.jadehelena.banking.controller.exception.PersonHasActiveAccountException
import com.jadehelena.banking.controller.form.PersonForm
import com.jadehelena.banking.model.Account
import com.jadehelena.banking.model.Person
import com.jadehelena.banking.repository.AccountRepository
import com.jadehelena.banking.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Sort
import javax.persistence.EntityNotFoundException

@Service
class PersonService {
    @Autowired
    PersonRepository personRepository

    @Autowired
    AccountRepository accountRepository

    List findAll() {
        personRepository.findAll(Sort.by('name')).asList()
    }

    Person findById(long id) {
        personRepository.findById(id).orElse(null)
    }

    Person findByIdOrError(long id) {
        personRepository.findById(id).orElseThrow({
            new EntityNotFoundException()
        })
    }

    def save(Person person){
        personRepository.save(person)
    }

    def update(PersonForm personForm, long id) {
        Person persistedPerson = findByIdOrError(id)

        persistedPerson.with {
            name = personForm.name
            lastname = personForm.lastname
            document = personForm.document
        }

        personRepository.save(persistedPerson)
    }

    def deleteById(long id) {
        throwErrorIfPersonHasAccount(id)

        Person person = findByIdOrError(id)
        personRepository.delete(person)
        person
    }

    private def throwErrorIfPersonHasAccount(holderId) {
        Account account = accountRepository.findByHolderId(holderId)

        if(account != null) {
            throw new PersonHasActiveAccountException()
        }
    }

}