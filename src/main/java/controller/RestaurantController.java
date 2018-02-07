package controller;

import java.util.Hashtable;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import Model.Item;
import Model.Menu;
import Model.Restaurant;
import Model.RestaurantMenu;
import repository.RestaurantRepository;
import service.RestaurantService;
import util.CustomErrorType;

@RestController
@RequestMapping("")
@Api(name="Restfood Api", 
	description = "List of methods that manage restaurant", 
	stage =ApiStage.UNDEFINED )
public class RestaurantController {
    public static final Logger logger = LogManager.getLogger(RestaurantController.class.getName());

	
	
	@Autowired
	
	
	RestaurantService rs;
	
	
	
	// Get all Restaurant
	@RequestMapping(value="/restaurants",method=RequestMethod.GET)
	@ApiMethod(description = "Get all restaurants")
    public ResponseEntity<Hashtable<String, Restaurant>> listAllRestaurants() {
		Hashtable<String, Restaurant> restaurants = rs.getAll();
        if (restaurants.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<Hashtable<String, Restaurant>>(restaurants, HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/menu",method=RequestMethod.GET)
	@ApiMethod(description = "Get all menus")
    public ResponseEntity<Hashtable<String, RestaurantMenu>> listAllMenu() {
		Hashtable<String, RestaurantMenu> menus = rs.getAllMenu();
        if (menus.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, RestaurantMenu>>(menus, HttpStatus.OK);
    }
	
	@RequestMapping(value="/items",method=RequestMethod.GET)
	@ApiMethod(description = "Get all items of the menu")
    public ResponseEntity<Hashtable<String, Item>> listAllItems() {
		Hashtable<String, Item> items = rs.getAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, Item>>(items, HttpStatus.OK);
    }
	
	
		//Get Restaurant by id
	
	@RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/restaurants/{id}/menu/{menuId}", method = RequestMethod.GET)
	@ApiMethod(description = "Get Menu of a Resturant by id")
	public ResponseEntity<?> getMenu(@ApiPathParam(name= "id", description="Restaurant's ID") @PathVariable("id") long id,@PathVariable("menuId") long menuId) {
		logger.info("Getting Restaurants info with id "+id);
		
		RestaurantMenu menu = rs.getMenubyid(id,menuId);
		if (menu == null) {
			logger.error("Unable to Get. Menu with id "+ menuId+" not found.");
			return new ResponseEntity(new CustomErrorType("Unable to get. Restaurant with id " + menuId + " not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity< RestaurantMenu>(menu,HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/restaurants/{id}/menu",method=RequestMethod.GET)
	@ApiMethod(description = "Get all menus of a Restaurants")
    public ResponseEntity<Hashtable<String, RestaurantMenu>> MenuByRestaurant(@ApiPathParam(name= "id", description="Restaurant's ID") @PathVariable("id") long id) {
		Hashtable<String, RestaurantMenu> menus = rs.getMenuByRestaurant(id);
        if (menus.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, RestaurantMenu>>(menus, HttpStatus.OK);
    }
	
	@RequestMapping(value="/restaurants/{id}/menu/{menuId}/items",method=RequestMethod.GET)
	@ApiMethod(description = "Get all items of the menu")
    public ResponseEntity<Hashtable<String, Item>> itemsbyMenu(@ApiPathParam(name= "id", description="Restaurant's ID") @PathVariable("id") long id, @PathVariable("menuId") long menuId) {
		Hashtable<String, Item> items = rs.getItemsbymenu(id,menuId);
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, Item>>(items, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = "/restaurants/{id}/menu/{menuId}/items/{itemId}", method = RequestMethod.GET)
	@ApiMethod(description = "Get Items in a Restaurant's menu by id")
	public ResponseEntity<?> getItem(@ApiPathParam(name= "id") @PathVariable("menuId") long menuId,@PathVariable("itemId") long itemId) {
       logger.info("Getting Restaurants info with id "+itemId);
 
        Item menu = rs.getItembyid(menuId, itemId);
        if (menu == null) {
            logger.error("Unable to Get. Menu with id "+ itemId+" not found.");
            return new ResponseEntity(new CustomErrorType("Unable to get. Restaurant with id " + itemId + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity< Item>(menu,HttpStatus.OK);
    }
	
	
	
		// Delete restaurant by id
	@RequestMapping(value = "/restaurants/{id}", method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete Restaurants ")
	public ResponseEntity<?> deleteUser(@ApiPathParam(name = "id") @PathVariable("id") long id) {
       logger.info("Fetching & Deleting Restaurant with id "+id);
 
        Restaurant restaurant = rs.getRestaurant(id);
        if (restaurant == null) {
            logger.error("Unable to delete. Restaurant with id {} not found."+ id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Restaurant with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        rs.deleteRestById(id);
        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }
	
	 	//Post by id
	   @RequestMapping(value = "/restaurants/", method = RequestMethod.POST)
	   @ApiMethod(description = "Create Restaurants ")
	   public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating restaruant "+ restaurant.getName());
	 
	        if (rs.isRestaurantExist(restaurant)) {
	            logger.error("Unable to create. A User with name "+restaurant.getName()+" already exist" );
	            return new ResponseEntity(new CustomErrorType("Unable to create. A Restaurant with name " + 
	            restaurant.getName() + " already exist."),HttpStatus.CONFLICT);
	        }
	        rs.saveRestaurant(restaurant);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(restaurant.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	 
	   /*
		/REpository
		RestaurantRepository repo; 
		@RequestMapping(value="/menu",method=RequestMethod.GET)
		public List<Menu> getAllMenu() {
		    return rs.getdbmenu();
		}
		@RequestMapping(value="/menu",method=RequestMethod.POST)
		public Menu createMenu(@Valid @RequestBody Menu menu) {
		    return rs.save(menu);
		}
		
		
		
		
		
		@RequestMapping(value="/menu",method=RequestMethod.POST)
		public Menu createNote(@Valid @RequestBody Menu menu) {
		    return repo.save(menu);
		}
		
		@GetMapping("/menu/{id}")
		public ResponseEntity<Menu> getNoteById(@PathVariable(value = "id") Long menuId) {
		    Menu menu = repo.findOne(menuId);
		    if(menu == null) {
		        return ResponseEntity.notFound().build();
		    }
		    return ResponseEntity.ok().body(menu);
		}
		
		
		
		*/
		
	 
	
}
