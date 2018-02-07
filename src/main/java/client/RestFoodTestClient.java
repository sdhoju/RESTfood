package client;

import java.net.URI;
import java.util.Hashtable;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import Model.Restaurant;

public class RestFoodTestClient {
    public static final String REST_SERVICE_URI = "http://localhost:8888";
    
    @SuppressWarnings("unchecked")
    private static void listAllRestaurants(){
        System.out.println("Testing listAllRestaurants API-----------");
        
         
        RestTemplate restTemplate = new RestTemplate();
        Hashtable<String, Object> restaurant = restTemplate.getForObject(REST_SERVICE_URI+"/restaurants/",Hashtable.class);
 
        if(restaurant!=null){
        	
        	Set<String> keys = restaurant.keySet();
	        for(String key: keys){
	        	System.out.println(restaurant.get(key).toString());
	        }
        }else{
            System.out.println("No Restaurants exist----------");
        }
    }private static void getRestaurant(){
    	 System.out.println("Testing getRestaurant API----------");
         RestTemplate restTemplate = new RestTemplate();
         Restaurant restaurant = restTemplate.getForObject(REST_SERVICE_URI+"/restaurants/1", Restaurant.class);
         System.out.println("Restaurant with id: "+ restaurant.getId()+", Name '"+restaurant.getName()+"', Phone: "+restaurant.getPhone());
    }
    private static void createRestaurant(){
   	 System.out.println("Testing createRestaurant API----------");
        RestTemplate restTemplate = new RestTemplate();
        Restaurant restaurant = new Restaurant(5,"Central Perk",654798);
        System.out.println("Restaurant with id: "+ restaurant.getId()+", Name '"+restaurant.getName()+"', Phone: "+restaurant.getPhone());
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/restaurants/", restaurant, Restaurant.class);
        System.out.println("Location : "+uri.toASCIIString());
    }
    private static void deleteRestaurant(){
      	 System.out.println("Testing deleteRestaurant API----------");
           RestTemplate restTemplate = new RestTemplate();
           restTemplate.delete(REST_SERVICE_URI+"/restaurants/5");
       }
    
    private static void deleteAllRestaurant(){
     	 System.out.println("Testing deleteAllRestaurant API----------");
          RestTemplate restTemplate = new RestTemplate();
          restTemplate.delete(REST_SERVICE_URI+"/restaurants/");
      }
    
    public static void main(String args[]){
    	listAllRestaurants();
    	getRestaurant();
    	createRestaurant();
    	listAllRestaurants();
    	deleteRestaurant();
    	listAllRestaurants();
    	deleteAllRestaurant();
    	listAllRestaurants();
    }
}
