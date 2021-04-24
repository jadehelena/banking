package com.jadehelena.banking.controller.dto

import com.jadehelena.banking.model.Account

class AccountDto {
    private Long id
    private int number
    private int agency
    private BigDecimal balance

    AccountDto(Account account) {
        this.id = account.getId()
        this.number = account.getNumber()
        this.agency = account.getAgency()
        this.balance = account.getBalance()
    }

    Long getId() {
        return id
    }

    int getNumber() {
        return number
    }

    int getAgency() {
        return agency
    }

    BigDecimal getBalance() {
        return balance
    }

}
