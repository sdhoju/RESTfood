package controller;

import java.util.Hashtable;
import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiStage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.Bistro;
import com.example.demo.ItemRepository;
import com.example.demo.Menu;
import com.example.demo.MenuRepository;
import com.example.demo.RestaurantRepository;
import com.example.demo.restItem;

import Model.Item;
import Model.Restaurant;
import Model.RestaurantMenu;
import service.RestaurantService;
import util.CustomErrorType;

@RestController
@RequestMapping("")
@Api(name="Restfood Api", 
	description = "List of methods that manage restaurant", 
	stage =ApiStage.UNDEFINED )
public class RestaurantController {
    public static final Logger logger = LogManager.getLogger(RestaurantController.class.getName());

	//Calling Repositories
    @Qualifier("restaurantRepository")
	@Autowired
	private RestaurantRepository restaurantRepository;
    
    @Qualifier("menuRepository")
  	@Autowired
  	private MenuRepository menuRepository;
    
    @Qualifier("itemRepository")
  	@Autowired
  	private ItemRepository itemRepository;
    
    @Autowired
    RestaurantService rs;
	
    //Methods for Bistros
	@RequestMapping(value="/db/bistros",method=RequestMethod.GET)
	@ApiMethod(description = "Get all Restaurants")
    public ResponseEntity<List<Bistro>> getAllItem() {
		List<Bistro> bistro = restaurantRepository.findAll();
        if (bistro.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Bistro>>(bistro, HttpStatus.OK);
    }
	//Get method for Restaurant by id
	@RequestMapping(value="/db/bistros/{restId}",method=RequestMethod.GET)
	@ApiMethod(description = "Search for the Restaurant's info by id")
    public ResponseEntity<Bistro> getRestaurant(@ApiPathParam(name= "id") @PathVariable("restId") long id) {
		Bistro rest =  restaurantRepository.findOne(id);
        if (rest==null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Bistro>(rest, HttpStatus.OK);
    }
	
	//Delete method for Restaurant by id
	@RequestMapping(value="/db/bistros/{restId}",method=RequestMethod.DELETE)
	@ApiMethod(description = "Close the restaurant")
    public ResponseEntity<Bistro> deletRestaurant(@ApiPathParam(name= "id") @PathVariable("restId") long id) {
		restaurantRepository.delete(id);
        return new ResponseEntity<Bistro>(HttpStatus.NO_CONTENT);
    }
	
	
	//Method for menus
	@RequestMapping(value="/db/bistros/{restId}/menu",method=RequestMethod.GET)
	@ApiMethod(description = "Get all menus")
    public ResponseEntity<List<Menu>> getAllmenu(@ApiPathParam(name= "id") @PathVariable("restId") long id) {
		List<Menu> menu = menuRepository.findByBistroId(id);
        if (menu.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Menu>>(menu, HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/db/{restId}/menu/",method=RequestMethod.POST) 
    @ApiMethod(description = "Add a menu to db in menu table")
	public ResponseEntity<List<Menu>>addNewMenu (@RequestBody Menu menu, @PathVariable("restID") long id) {
		if(menuRepository.findOne(menu.getId())==null) {
			menuRepository.save(menu);
			return new ResponseEntity<List<Menu>>(menuRepository.findAll(), HttpStatus.OK);
		}
		else
			return new ResponseEntity<List<Menu>>(HttpStatus.CONFLICT);
    } 
	
	@RequestMapping(value="/db/bistros/{restId}/menu/",method=RequestMethod.DELETE) 
    @ApiMethod(description = "Delete all menu of a restaurant ")
	public ResponseEntity<List<Menu>>deleteAllMenu ( @PathVariable("restId") long id) {
			menuRepository.delete(id);
			return new ResponseEntity<List<Menu>>(HttpStatus.CONFLICT);
    } 
	
	
	@RequestMapping(value="/db/bistros/{restId}/menu/{menuId}",method=RequestMethod.DELETE) 
    @ApiMethod(description = "Delete a menu from database in menu table by id")
	public ResponseEntity<List<Menu>>deleteMenu(@ApiPathParam(name= "id") @PathVariable("menuId") long menuId) {
		menuRepository.delete(menuId);
		return new ResponseEntity<List<Menu>>(menuRepository.findAll(),HttpStatus.OK); 
    } 
	
	
	@RequestMapping(value="/db/menu/",method=RequestMethod.PUT) 
    @ApiMethod(description = "Change a menu to db in menu table")
	public ResponseEntity<List<Menu>>updateMenu(@RequestBody Menu menu) {
		menuRepository.save(menu);
		return new ResponseEntity<List<Menu>>(menuRepository.findAll(),HttpStatus.OK); 
	} 
	
	@RequestMapping(value="/db/menu/",method=RequestMethod.DELETE) 
    @ApiMethod(description = "Change a menu to db in menu table")
	public ResponseEntity<List<Menu>>deleteAllMenu() {
		menuRepository.deleteAll();;
		return new ResponseEntity<List<Menu>>(HttpStatus.NO_CONTENT); 
    } 
	
	
	
	
	
	/*
	
	// Get all Restaurant
	@RequestMapping(value="/mem",method=RequestMethod.GET)
    public ResponseEntity<?> mem() {

        return new ResponseEntity<String>("Using Memory", HttpStatus.OK);
    }
	
	@RequestMapping(value="/mem/restaurants",method=RequestMethod.GET)
	@ApiMethod(description = "Get all restaurants")
    public ResponseEntity<Hashtable<String, Restaurant>> listAllRestaurants() {
		Hashtable<String, Restaurant> restaurants = rs.getAll();
        if (restaurants.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<Hashtable<String, Restaurant>>(restaurants, HttpStatus.OK);
    }
	
	
	@RequestMapping(value="/mem/menu",method=RequestMethod.GET)
	@ApiMethod(description = "Get all menus")
    public ResponseEntity<Hashtable<String, RestaurantMenu>> listAllMenu() {
		Hashtable<String, RestaurantMenu> menus = rs.getAllMenu();
        if (menus.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, RestaurantMenu>>(menus, HttpStatus.OK);
    }
	
	@RequestMapping(value="/mem/items",method=RequestMethod.GET)
	@ApiMethod(description = "Get all items of the menu")
    public ResponseEntity<Hashtable<String, Item>> listAllItems() {
		Hashtable<String, Item> items = rs.getAllItems();
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, Item>>(items, HttpStatus.OK);
    }
	
	
		//Get Restaurant by id
	
	@RequestMapping(value = "/mem/restaurants/{id}", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value = "/mem/restaurants/{id}/menu/{menuId}", method = RequestMethod.GET)
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
	
	
	@RequestMapping(value="/mem/restaurants/{id}/menu",method=RequestMethod.GET)
	@ApiMethod(description = "Get all menus of a Restaurants")
    public ResponseEntity<Hashtable<String, RestaurantMenu>> MenuByRestaurant(@ApiPathParam(name= "id", description="Restaurant's ID") @PathVariable("id") long id) {
		Hashtable<String, RestaurantMenu> menus = rs.getMenuByRestaurant(id);
        if (menus.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, RestaurantMenu>>(menus, HttpStatus.OK);
    }
	
	@RequestMapping(value="/mem/restaurants/{id}/menu/{menuId}/items",method=RequestMethod.GET)
	@ApiMethod(description = "Get all items of the menu")
    public ResponseEntity<Hashtable<String, Item>> itemsbyMenu(@ApiPathParam(name= "id", description="Restaurant's ID") @PathVariable("id") long id, @PathVariable("menuId") long menuId) {
		Hashtable<String, Item> items = rs.getItemsbymenu(id,menuId);
        if (items.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Hashtable<String, Item>>(items, HttpStatus.OK);
    }
	
	
	
	@RequestMapping(value = "/mem/restaurants/{id}/menu/{menuId}/items/{itemId}", method = RequestMethod.GET)
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
	
	 @RequestMapping(value = "/mem/restaurants/", method = RequestMethod.DELETE)
	    public ResponseEntity<Restaurant> deleteAllRestaurant() {
	        logger.info("Deleting All Users");
	        rs.deleteAllRestaurant();
	        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
	    }
	
		// Delete restaurant by id
	@RequestMapping(value = "/mem/restaurants/{id}", method = RequestMethod.DELETE)
	@ApiMethod(description = "Delete Restaurants ")
	public ResponseEntity<?> deleteRestaurant(@ApiPathParam(name = "id") @PathVariable("id") long id) {
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
	   @RequestMapping(value = "/mem/restaurants/", method = RequestMethod.POST)
	   @ApiMethod(description = "Create Restaurants ")
	   public ResponseEntity<?> createRestaurant(@RequestBody Restaurant restaurant, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating restaruant "+ restaurant.getName());
	 
	        if (rs.isRestaurantExist(restaurant)) {
	            logger.error("Unable to create. A Restaurant with id "+restaurant.getId()+" already exist" );
	            return new ResponseEntity(new CustomErrorType("Unable to create. A Restaurant with id " + 
	            restaurant.getId() + " already exist."),HttpStatus.CONFLICT);
	        }
	        rs.saveRestaurant(restaurant);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(restaurant.getId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	   
	   @RequestMapping(value = "/mem/restaurants/{id}", method = RequestMethod.POST)
	   @ApiMethod(description = "Create Restaurant's Menu")
	   public ResponseEntity<?> createMenu(@ApiPathParam(name = "id") @PathVariable("id") long id, @RequestBody RestaurantMenu menu, UriComponentsBuilder ucBuilder) {
	        logger.info("Creating Menu "+ menu.getMenuName());
	        if (rs.isMenuExist(id,menu)) {
	            logger.error("Unable to create. A User with id "+menu.getMenuId()+" already exist" );
	            return new ResponseEntity(new CustomErrorType("Unable to create. A Menu with id " + 
	            menu.getMenuId() + " already exist."),HttpStatus.CONFLICT);
	        }
	        rs.saveMenu(menu);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/{menuId}").buildAndExpand(menu.getMenuId()).toUri());
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
	   
	   
	   
	   @RequestMapping(value = "/mem/restaurants/{id}/menu/{id}", method = RequestMethod.DELETE)
	   @ApiMethod(description = "Delete Restaurant's Menu")
	   public ResponseEntity<?> deleteMenu(@ApiPathParam(name = "id") @PathVariable("id") long id, @PathVariable("menuId") long menuId) {
	        logger.info("Deleting Menu with"+ menuId);
	        RestaurantMenu menu=rs.getMenubyid(id, menuId);
	        if (menu==null) {
	            logger.error("Unable to Delete the menu with id  "+menuId );
	            return new ResponseEntity(new CustomErrorType("Unable to Delete. A Menu with id " + 
	            menuId+ ""),HttpStatus.NOT_FOUND);
	        }
	        rs.deleteMenuById(id,menuId);
	        HttpHeaders headers = new HttpHeaders();
	        return new ResponseEntity<RestaurantMenu>( HttpStatus.NO_CONTENT);
	    }
	*/
}
