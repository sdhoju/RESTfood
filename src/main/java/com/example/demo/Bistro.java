package com.example.demo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

@Entity
public class Bistro {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	private String name;
	private long phone;
	
//	@OneToMany(fetch = FetchType.LAZY, cascade=(CascadeType.ALL),orphanRemoval = true)
//	List<Menu> menus;
	//Constructors
	public Bistro() {
		
	}
	public Bistro(long restId, String name, long phone) {
		this.id=restId;
		this .name=name;
		this.phone=phone;
		
	}
	
	//Getter and setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	

}
