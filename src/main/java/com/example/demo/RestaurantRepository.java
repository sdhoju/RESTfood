package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository  extends JpaRepository<Bistro, Long>{
	public Bistro findByName(String name);
}
