package com.jadehelena.banking.service

import com.jadehelena.banking.controller.form.PersonForm
import com.jadehelena.banking.model.Person
import com.jadehelena.banking.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.data.domain.Sort

@Service
class PersonService {
    @Autowired
    PersonRepository personRepository

    @Autowired
    AccountService accountService

    List findAll() {
        personRepository.findAll(Sort.by('name')).asList()
    }

    Person findById(long id) {
        personRepository.findById(id).orElse(null)
    }

    def save(Person person){
        def persistedPerson = personRepository.findByDocument(person.getDocument())
        if(persistedPerson != null) {
            throw new IllegalArgumentException("This document is already registered")
        }

        personRepository.save(person)
    }

    def update(PersonForm personForm, long id) {
        Person persistedPerson = findById(id)

        persistedPerson.with {
            name = personForm.name
            lastname = personForm.lastname
            document = personForm.document
        }

        personRepository.save(persistedPerson)
    }

    def deleteById(long id) {
        accountService.validatesIfHolderDoesntHaveAccount(id)

        Person person = findById(id)
        personRepository.delete(person)
        person
    }

}