package com.example.demo;


import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import Model.Restaurant;

@Entity

public class Menu implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//Restaurant restaurant;
	

	private String name;
	//long restID;


	public String getName() {
		return name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	
}
