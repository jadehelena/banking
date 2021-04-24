package com.jadehelena.banking.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.DiscriminatorColumn
import javax.persistence.Inheritance
import javax.persistence.InheritanceType
import javax.persistence.DiscriminatorType
import javax.persistence.OneToOne
import javax.persistence.JoinColumn


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
class Holder {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    private String name
    private String document

    @OneToOne(mappedBy = "holder")
    private Account account

    Long getId() {
        return id
    }

    String getName() {
        return name
    }

    void setName(String name) {
        this.name = name
    }

    String getDocument() {
        return document
    }

    void setDocument(String document) {
        this.document = document
    }
}
