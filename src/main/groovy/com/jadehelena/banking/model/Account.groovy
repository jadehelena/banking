package com.jadehelena.banking.model;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Account {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int number;
    private int agency;
    private BigDecimal balance;
    
	@OneToMany(mappedBy = "account")
    private List<Transaction> transactions;
    
    @OneToOne(mappedBy = "account")
    private Holder holder;

}