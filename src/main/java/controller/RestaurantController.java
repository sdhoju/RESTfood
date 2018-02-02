package controller;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.Restaurant;
import service.RestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
	
	@Autowired
	RestaurantService rs;
	@RequestMapping("/all")
	public Hashtable<String, Restaurant> getAll(){
		return rs.getAll();
		
	}
	@RequestMapping("{id}")
	public Restaurant getRestaurant(@PathVariable("id") String id) {
		return rs.getRestaurant(id);
	}
}
