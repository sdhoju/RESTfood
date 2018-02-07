package service;

import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import Model.Item;
import Model.Menu;
import Model.Restaurant;
import Model.RestaurantMenu;
import repository.RestaurantRepository;

@Service
public class RestaurantService {
	private static final AtomicLong counter = new AtomicLong();
	
	 public Hashtable<String, Restaurant> restaurants = new Hashtable<String, Restaurant>();
	 public Hashtable<String, RestaurantMenu> menus = new Hashtable<String, RestaurantMenu>();
	 public Hashtable<String, Item> items = new Hashtable<String, Item>();


	 public Restaurant getRestaurant(long id) {
		
		if(restaurants.containsKey(String.valueOf(id)))
			return restaurants.get(String.valueOf(id));
		else
			return null;
	}
	public  RestaurantMenu getMenubyid(long id,long menuId) {
		if(menus.containsKey(String.valueOf(menuId)))
			if(menus.get(String.valueOf(menuId)).getRestId()==id) {
				return menus.get(String.valueOf(menuId));
			}
			else {
				return null;
			}
				
		else
			return null;
	}
	
	public  Item getItembyid(long menuId,long itemId) {
		if(items.containsKey(String.valueOf(itemId)))
			if(items.get(String.valueOf(itemId)).getMenuId()==menuId &&items.get(String.valueOf(itemId)).getItemId()==itemId) {
				return items.get(String.valueOf(itemId));
			}
			else {
				return null;
			}
		else
			return null;
	}
	
	public Hashtable<String, RestaurantMenu> getMenuByRestaurant(long id){
		Hashtable<String, RestaurantMenu> tempHash = new Hashtable<String, RestaurantMenu>();
		RestaurantMenu tempMenu;
		Set<String> keys = menus.keySet();
	        for(String key: keys){
	            tempMenu=menus.get(key);
	            if(tempMenu.getRestId()==id) {
	            	tempHash.put(String.valueOf(tempMenu.getMenuId()), tempMenu);
	            }
	        }
		return tempHash;
	}
	
	public Hashtable<String, Item> getItemsbymenu(long id,long menuId){
		Hashtable<String, Item> tempHash = new Hashtable<String, Item>();
		Item tempitem;
		Set<String> keys = items.keySet();
	        for(String key: keys){
	        	tempitem=items.get(key);
	            if(tempitem.getMenuId()==menuId && menus.get(String.valueOf(tempitem.getMenuId())).getRestId()==id) {
	            	tempHash.put(key, tempitem);
	            }
	        }
		return tempHash;
	}
	/*public 
	 Set<String> keys = hm.keySet();
        for(String key: keys){
            System.out.println("Value of "+key+" is: "+hm.get(key));
        }
	*/
	
	//get all
	public Hashtable<String, Restaurant> getAll(){
		return restaurants;

	}
	public Hashtable<String, RestaurantMenu> getAllMenu(){
		return menus;
	}
	
	public Hashtable<String, Item> getAllItems(){
		return items;
	}
	
   public  void saveRestaurant(Restaurant restaurant) {
	   restaurants.put(String.valueOf(restaurant.getId()), restaurant);
    }
	
	
	public void deleteRestById(long id) {
		restaurants.remove(String.valueOf(id));
	}
	public void deleteAllRestaurant() {
		restaurants.clear();
		
	}
	public   boolean isRestaurantExist(Restaurant restaurant) {
		return getRestaurant(restaurant.getId())!=null;
	}

	public RestaurantService() {
		//This is what happens when Database doesnt work
		
		Restaurant rest1 = new Restaurant(1,"Joey's Favourite Sandwich shop",987456123);
		//Drinks
		Item i1 = 	new Item(1,"Diet Coke",1,1);
		Item i2 = 	new Item(2,"Mountain Dew",1.25,1);
		Item i3 = 	new Item(3,"Bottle Water",1.25,1);
		Item i4 = 	new Item(4,"Sprite",1.25,01);
		items.put("1", i1);
		items.put("2", i2);
		items.put("3", i3);
		items.put("4", i4);
		RestaurantMenu drinks = new RestaurantMenu(1,"Drinks",1);
		drinks.setItems(items);
		menus.put(String.valueOf(drinks.getMenuId()),drinks);
		//items.clear();
		
		//Sandwich
		Item i5 = 	new Item(5,"Ole Wrap",7.25,2);
		Item i6 = 	new Item(6,"Ole Wrap with fires",9.25,2);
		Item i7 = 	new Item(7,"Cool Ranch Wrap",7.25,2);
		Item i8 = 	new Item(8,"Cool Ranch with Wrap",9.25,2);
		Item i9 = 	new Item(9,"Spicy Buffalo Wrap",7.25,2);
		Item i10 = 	new Item(10,"Spicy Buffalo Wrap with fries",9.25,2);
		items.put("5", i5);
		items.put("6", i6);
		items.put("7", i7);
		items.put("8", i8);
		items.put("9", i9);
		items.put("10", i10);
		RestaurantMenu wich = new RestaurantMenu(2,"Sandwichs",1);
		wich.setItems(items);
		//items.clear();
		menus.put(String.valueOf(wich.getMenuId()),wich);
		
		//Chicken only
		Item i11 = 	new Item(12,"7 Tenders",10.25,3);
		Item i12 = 	new Item(13,"10 Tenders",12.25,3);
		Item i21 = 	new Item(21,"25 Tenders",29.25,3);
		items.put("11", i11);
		items.put("12", i12);
		items.put("21", i21);
		RestaurantMenu chick = new RestaurantMenu(3,"Chickens",1);
		chick.setItems(items);
		//items.clear();
		menus.put(String.valueOf(chick.getMenuId()),chick);
		rest1.setMenus(menus);
		restaurants.put(String.valueOf(rest1.getId()), rest1);
//		menus.clear();
		

		
		Item i22 = 	new Item(1,"Burger",3.25,2);
		Item i23 = 	new Item(1,"Burger",3.25,2);
		Item i24 = 	new Item(1,"Burger",3.25,2);
		Item i25 = 	new Item(1,"Burger",3.25,2);
		Item i26 = 	new Item(1,"Burger",3.25,2);
		Item i27 = 	new Item(1,"Burger",3.25,2);
		Item i28 = 	new Item(1,"Burger",3.25,2);
		Item i29 = 	new Item(1,"Burger",3.25,2);
		Item i30 = 	new Item(1,"Burger",3.25,2);
		Item i31 = 	new Item(1,"Burger",3.25,2);
		Item i32 = 	new Item(1,"Burger",3.25,2);
		
		
		
		
		Restaurant r = new Restaurant();
		r.setId(11);
		r.setName("Kabuki");	
		r.setPhone(12345);
		r.setMenus(menus);
		restaurants.put(String.valueOf(r.getId()), r);
		
		r = new Restaurant();
		r.setId(2);
		r.setName("McDonalds");	
		r.setPhone(12345);
		r.setMenus(menus);
		restaurants.put(String.valueOf(r.getId()), r);
		
		
		
		RestaurantMenu m = new RestaurantMenu();
		m.setMenuId(11);
		m.setMenuName("Breakfast");
		m.setRestId(r.getId());
		m.setItems(items);
		menus.put(String.valueOf(m.getMenuId()), m);
		
		m = new RestaurantMenu();
		m.setMenuId(12);
		m.setMenuName("Lunch");
		m.setItems(items);
		menus.put(String.valueOf(m.getMenuId()), m);
	}
	/*
	//@Autowired
	RestaurantRepository repo;
	
	public List<Menu> getdbmenu() {
		System.out.println("In");
		try {
			return (List<Menu>) repo.findAll();
		} catch (Exception e) {
			return null;
		}
	}
	public Menu save(Menu menu) {
		try {
			return repo.save(menu);
		} catch (Exception e) {
			return null;
		}
	}
	*/
	
	

	
	
}