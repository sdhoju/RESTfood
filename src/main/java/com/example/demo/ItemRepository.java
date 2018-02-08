package com.example.demo;


import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository  extends JpaRepository<restItem, Long>{
	public List<restItem> findByMenuId(long id);
}
