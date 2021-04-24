package com.jadehelena.banking.model

import java.time.LocalDateTime

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Transaction {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id
    private BigDecimal value
    private LocalDateTime createdAt
    private String type

    @ManyToOne
	private Account to_account

    @ManyToOne
    private Account from_account


    Long getId() {
        return id
    }

    BigDecimal getValue() {
        return value
    }

    void setValue(BigDecimal value) {
        this.value = value
    }

    LocalDateTime getCreatedAt() {
        return createdAt
    }

    void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt
    }

    Account getToAccount() {
        return to_account
    }

    void setToAccount(Account to_account) {
        this.to_account = to_account
    }

    Account getFromAccount() {
        return from_account
    }

    void setFromAccount(Account from_account) {
        this.from_account = from_account
    }

    String getType() {
        return type
    }

    void setType(String type) {
        this.type = type
    }
}