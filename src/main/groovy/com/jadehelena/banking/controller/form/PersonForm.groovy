package com.jadehelena.banking.controller.form

import com.jadehelena.banking.model.Person

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

class PersonForm {
    @NotNull @NotEmpty
    private String name
    @NotNull @NotEmpty
    private String lastname
    @NotNull @NotEmpty @Size(min = 11, max = 11, message = "document must have 11 numbers")
    private String document

    String getName() {
        return name
    }

    String getLastname() {
        return lastname
    }

    String getDocument() {
        return document
    }

    def convertToPerson() {
        return new Person(name, lastname, document)
    }

}
