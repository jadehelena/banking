package com.jadehelena.banking.service

import com.jadehelena.banking.model.Person
import com.jadehelena.banking.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService {
    @Autowired
    PersonRepository PersonRepository

    def save(Person person){
        PersonRepository.save(person)
    }

}