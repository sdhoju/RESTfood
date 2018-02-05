package controller;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.apache.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import Model.Restaurant;
import service.RestaurantService;
import util.CustomErrorType;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {
    public static final Logger logger = LogManager.getLogger(RestaurantController.class.getName());

	
	
	@Autowired
	RestaurantService rs;
	@RequestMapping("/all")
	public Hashtable<String, Restaurant> getAll(){
		return rs.getAll();
		
	}
	@RequestMapping("{id}")
	public Restaurant getRestaurant(@PathVariable("id") long id) {
		return rs.getRestaurant(id);
	}
	 @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
	       logger.info("Fetching & Deleting User with id "+id);
	 
	        Restaurant user = rs.getRestaurant(id);
	        if (user == null) {
	            logger.error("Unable to delete. User with id {} not found."+ id);
	            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
	                    HttpStatus.NOT_FOUND);
	        }
	        rs.deleteUserById(id);
	        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
	    }
	
	
}
