package com.jadehelena.banking.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("F")
public class Person extends Holder {
	private String lastname;
}