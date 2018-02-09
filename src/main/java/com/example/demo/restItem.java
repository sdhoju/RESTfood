package com.example.demo;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

//This is the Item Resource
@Entity
public class restItem  {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@JsonIgnore
	@ManyToOne
	private Menu menu;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	private String item;
	private double price;
	
	
	public restItem() {
		// TODO Auto-generated constructor stub
	}
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
	public restItem(long id,String name, double price) {
		this.id=id;
		this.item= name;
		this.price=price;
	}
	public restItem(String name, double price) {
		this.item= name;
		this.price=price;
	}
	
}
