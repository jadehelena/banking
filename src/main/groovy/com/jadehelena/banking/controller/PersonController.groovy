package com.jadehelena.banking.controller

import com.jadehelena.banking.service.PersonService
import com.jadehelena.banking.model.Person

import javax.transaction.Transactional

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping('persons')
class PersonController {

    @Autowired
    PersonService personService

    @PostMapping
    @Transactional
    Person save(@RequestBody Person person) {
        personService.save(person)
    }

}
