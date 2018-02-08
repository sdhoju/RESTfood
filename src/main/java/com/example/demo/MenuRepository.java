package com.example.demo;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository  extends JpaRepository<Menu, Long>{
	public List<Menu> findByBistroId(long restId);
	public Menu findByIdAndBistroId(long menuId,long id);
	public List<Menu> findByName(String name);
	
}
