package com.jadehelena.banking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorType;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", length = 1, discriminatorType = DiscriminatorType.STRING)
public class Holder {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String document;

	@OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;
}
