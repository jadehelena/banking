package com.jadehelena.banking.model

import com.fasterxml.jackson.annotation.JsonBackReference
import org.hibernate.annotations.CreationTimestamp

import javax.persistence.Column
import javax.persistence.JoinColumn
import javax.validation.constraints.Digits
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
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

    @NotNull @Positive @Digits(integer=6, fraction=2)
    private BigDecimal value

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt

    @NotNull @NotEmpty
    private String type

    @ManyToOne
    @JoinColumn(name = "origin_account_id")
    @JsonBackReference("originAccount")
    private Account originAccount

    @ManyToOne
    @JoinColumn(name = "target_account_id")
    @JsonBackReference("targetAccount")
    private Account targetAccount

    enum transactType {
        DEPOSIT,
        TRANSFER
    }

    Transaction() {
    }

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

    Account getOriginAccount() {
        return originAccount
    }

    void setOriginAccount(Account originAccount) {
        this.originAccount = originAccount
    }

    Account getTargetAccount() {
        return targetAccount
    }

    void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount
    }

    String getType() {
        return type
    }

    void setType(String type) {
        this.type = type
    }
}