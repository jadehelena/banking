package com.jadehelena.banking.repository

import com.jadehelena.banking.model.Person
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository extends JpaRepository<Person, Long>  {
    Person findByDocument(String document)
}
