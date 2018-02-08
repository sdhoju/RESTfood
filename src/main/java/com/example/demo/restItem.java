package com.example.demo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class restItem  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String item;
	private double price;
	
	@ManyToOne
	private Menu menu;

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
}
