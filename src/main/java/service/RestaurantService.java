package service;

import java.util.Hashtable;
import java.util.Iterator;

import org.springframework.stereotype.Service;

import Model.Restaurant;

@Service
public class RestaurantService {
	Hashtable<String, Restaurant> restaurants = new Hashtable<String, Restaurant>();
	public RestaurantService() {
		Restaurant r = new Restaurant();
		r.setId(1);
		r.setName("Kabuki");	
		r.setPhone(12345);
		restaurants.put(String.valueOf(r.getId()), r);
		
		r = new Restaurant();
		r.setId(2);
		r.setName("McDonalds");	
		r.setPhone(12345);
		restaurants.put(String.valueOf(r.getId()), r);
	}
	public Restaurant getRestaurant(long id) {
		
		if(restaurants.containsKey(String.valueOf(id)))
			return restaurants.get(String.valueOf(id));
		else
			return null;
	}
	public Hashtable<String, Restaurant> getAll(){
		return restaurants;

}
	public void deleteUserById(long id) {
		restaurants.remove(String.valueOf(id));
	}
}