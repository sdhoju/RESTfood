package service;

import java.util.Hashtable;
import org.springframework.stereotype.Service;
import Model.Restaurant;

@Service
public class RestaurantService {
	Hashtable<String, Restaurant> restaurants = new Hashtable<String, Restaurant>();
	public RestaurantService() {
		Restaurant r = new Restaurant();
		r.setId("1");
		r.setName("Kabuki");	
		r.setPhone(12345);
		restaurants.put("1", r);
		
		 r = new Restaurant();
		r.setId("2");
		r.setName("McDonalds");	
		r.setPhone(12345);
		restaurants.put("2", r);
	}
	public Restaurant getRestaurant(String id) {
		if(restaurants.containsKey(id))
			return restaurants.get(id);
		else
			return null;
	}
	public Hashtable<String, Restaurant> getAll(){
		return restaurants;

}
}