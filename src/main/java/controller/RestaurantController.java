package controller;

import java.util.Hashtable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import Model.Restaurant;
import service.RestaurantService;
import util.CustomErrorType;

@RestController
@RequestMapping("/restaurants")
@Api(name="Restfood Api", 
	description = "List of methods that manage restaurant", 
	stage =ApiStage.UNDEFINED )
public class RestaurantController {
    public static final Logger logger = LogManager.getLogger(RestaurantController.class.getName());

	
	
	@Autowired
	RestaurantService rs;
	
	// Get all Restaurant
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ApiMethod(description = "Get all restaurants")
    public ResponseEntity<Hashtable<String, Restaurant>> listAllRestaurants() {
		Hashtable<String, Restaurant> restaurants = rs.getAll();
        if (restaurants.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<Hashtable<String, Restaurant>>(restaurants, HttpStatus.OK);
    }
	

		//Get Restaurant by id
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiMethod(description = "Get restaurants by id")
	public ResponseEntity<?> getRestaurant(@ApiPathParam(name= "id") @PathVariable("id") long id) {
       logger.info("Getting Restaurants info with id "+id);
 
        Restaurant restaurant = rs.getRestaurant(id);
        if (restaurant == null) {
            logger.error("Unable to Get. Restaurant  with id "+ id+" not found.");
            return new ResponseEntity(new CustomErrorType("Unable to get. Restaurant with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity< Restaurant>(restaurant,HttpStatus.OK);
    }
	
	
		// Delete restaurant by id
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete Restaurants ")
	public ResponseEntity<?> deleteUser(@ApiPathParam(name = "id") @PathVariable("id") long id) {
       logger.info("Fetching & Deleting Restaurant with id "+id);
 
        Restaurant restaurant = rs.getRestaurant(id);
        if (restaurant == null) {
            logger.error("Unable to delete. Restaurant with id {} not found."+ id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Restaurant with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        rs.deleteUserById(id);
        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }
	
	 //Post by id
	   @RequestMapping(value = "/", method = RequestMethod.POST)
	   @ApiMethod(description = "Create Restaurants ")
	   public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating restaruant "+ restaurant.getName());
	 
	        if (RestaurantService.isRestaurantExist(restaurant)) {
	            logger.error("Unable to create. A User with name "+restaurant.getName()+" already exist" );
	            return new ResponseEntity(new CustomErrorType("Unable to create. A Restaurant with name " + 
	            restaurant.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
	        RestaurantService.saveRestaurant(restaurant);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(restaurant.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 
	 
	 
	
}
