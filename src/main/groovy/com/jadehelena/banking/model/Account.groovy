package com.jadehelena.banking.model

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.ObjectIdGenerators

import javax.persistence.JoinColumn
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OneToOne

@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    private int number
    private int agency
    private BigDecimal balance

    Account() {
    }

    Account(Holder holder) {
        this.holder = holder
    }
    @OneToOne
    @JoinColumn(name = "holder_id", referencedColumnName = "id")
    private Holder holder

    Long getId() {
        return id
    }

    int getNumber() {
        return number
    }

    void setNumber(int number) {
        this.number = number
    }

    int getAgency() {
        return agency
    }

    void setAgency(int agency) {
        this.agency = agency
    }

    BigDecimal getBalance() {
        return balance
    }

    void setBalance(BigDecimal balance) {
        this.balance = balance
    }

    Holder getHolder() {
        return holder
    }

    void setHolder(Holder holder) {
        this.holder = holder
    }
}