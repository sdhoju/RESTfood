package service;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import Model.Restaurant;

@Service
public class RestaurantService {
	private static final AtomicLong counter = new AtomicLong();
	static Hashtable<String, Restaurant> restaurants = new Hashtable<String, Restaurant>();
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
	
	public static Restaurant getRestaurant(long id) {
		
		if(restaurants.containsKey(String.valueOf(id)))
			return restaurants.get(String.valueOf(id));
		else
			return null;
	}
	
	public Hashtable<String, Restaurant> getAll(){
		return restaurants;

	}
   public static void saveRestaurant(Restaurant restaurant) {
	   restaurants.put(String.valueOf(restaurant.getId()), restaurant);
    }
	
	
	public void deleteUserById(long id) {
		restaurants.remove(String.valueOf(id));
	}
	
	public static boolean isRestaurantExist(Restaurant restaurant) {
		return getRestaurant(restaurant.getId())!=null;
	}
	
}