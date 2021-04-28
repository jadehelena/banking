package com.jadehelena.banking.controller

import com.jadehelena.banking.controller.dto.ExceptionDto
import com.jadehelena.banking.exception.PersonHasActiveAccountException
import com.jadehelena.banking.controller.form.PersonForm
import com.jadehelena.banking.service.PersonService
import com.jadehelena.banking.model.Person
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.util.UriComponentsBuilder

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

import javax.validation.Valid

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
    ResponseEntity<Person> findOne(@PathVariable long id) {
        Person persistedPerson = personService.findById(id)
        if (persistedPerson != null) {
            return ResponseEntity.ok(persistedPerson)
        }

        return ResponseEntity.notFound().build()
    }

    @PostMapping
    @Transactional
    ResponseEntity<Person> save(@RequestBody @Valid PersonForm personForm, UriComponentsBuilder uriBuilder) {
        Person person = personForm.convertToPerson()
        personService.save(person)

        URI uri = uriBuilder.path("/people/{id}").buildAndExpand(person.getId()).toUri()
        return ResponseEntity.created(uri).body(person)
    }

    @PutMapping('{id}')
    @Transactional
    ResponseEntity<Person> update(@RequestBody @Valid PersonForm personForm, @PathVariable long id) {
        Person persistedPerson = personService.findById(id)
        if (persistedPerson != null) {
            personService.update(personForm, id)
            return ResponseEntity.ok(persistedPerson)
        }

        return ResponseEntity.notFound().build()
    }

    @DeleteMapping('{id}')
    ResponseEntity<Person> deleteById(@PathVariable long id) {
        try {
            Person persistedPerson = personService.findById(id)
            if (persistedPerson != null) {
                personService.deleteById(id)
                return ResponseEntity.ok().build()
            }
        } catch (PersonHasActiveAccountException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ExceptionDto(exception.getMessage()))
        }

        return ResponseEntity.notFound().build()
    }

}
