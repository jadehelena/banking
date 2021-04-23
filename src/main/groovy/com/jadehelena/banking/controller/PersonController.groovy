package com.jadehelena.banking.controller

import com.jadehelena.banking.service.PersonService
import com.jadehelena.banking.model.Person

import javax.transaction.Transactional

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable

@RestController
@RequestMapping('people')
class PersonController {

    @Autowired
    PersonService personService

    @GetMapping
    List findAll() {
        personService.findAll()
    }

    @GetMapping('{id}')
    Person findOne(@PathVariable long id) {
        personService.findById(id)
    }

    @PostMapping
    @Transactional
    Person save(@RequestBody Person person) {
        personService.save(person)
    }

    @PutMapping('{id}')
    @Transactional
    Person update(@RequestBody Person person, @PathVariable long id) {
        personService.update(person, id)
    }

    @DeleteMapping('{id}')
    Person deleteById(@PathVariable long id) {
        personService.deleteById(id)
    }

}
