package com.jadehelena.banking.model

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("F")
class Person extends Holder {
    private String lastname

    Person(String name, String lastname, String document) {
        this.name = name
        this.lastname = lastname
        this.document = document
    }

    Person() {

    }

    String getLastname() {
        return this.lastname
    }

    void setLastname(String lastname) {
        this.lastname = lastname
    }
}