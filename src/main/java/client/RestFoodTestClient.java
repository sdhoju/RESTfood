package client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;


import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Bistro;

import Model.Restaurant;

public class RestFoodTestClient {
    public static final String REST_SERVICE_URI = "http://localhost:8888";
    
    @SuppressWarnings("unchecked")

	 public static  void getAllBistro(){
    		RestTemplate restTemplate = new RestTemplate();
    		        System.out.println("Testing listAllRestaurants API-----------");
	        List<LinkedHashMap<String, Object>> bistros = restTemplate.getForObject(REST_SERVICE_URI+"/db/bistros/", List.class);
	        if(bistros!=null){
		        for(LinkedHashMap<String, Object> bistro: bistros){
		        	System.out.println("Bistro with id "+bistro.get("id")+". Name :"+bistro.get("name")+" Phone "+bistro.get("phone"));
		        }
	        }else{
	            System.out.println("No Restaurants exist----------");
	        } 
	   }

	private static void getRestaurant(){
		System.out.println("Testing getRestaurant API----------");
	    RestTemplate restTemplate = new RestTemplate();
	    Bistro restaurant = restTemplate.getForObject(REST_SERVICE_URI+"/db/bistros/1", Bistro.class);
	    System.out.println("Restaurant with id: "+ restaurant.getId()+", Name '"+restaurant.getName()+"', Phone: "+restaurant.getPhone());
    }
   
    private static void addBistro(){
	   	 	System.out.println("Testing createRestaurant API----------");
	        RestTemplate restTemplate = new RestTemplate();
	        Bistro restaurant = new Bistro(13,"Central Perk",654798789);
	        System.out.println("Restaurant with id: "+ restaurant.getId()+", Name '"+restaurant.getName()+"', Phone: "+restaurant.getPhone() +" is created.");
	        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/db/bistros/", restaurant, Restaurant.class);
	        System.out.println("Location : "+uri.toASCIIString());
    }
    
    private static void deleteRestaurant(){
     	 System.out.println("Testing deleteRestaurant API----------");
          RestTemplate restTemplate = new RestTemplate();
          restTemplate.delete(REST_SERVICE_URI+"/db/bistros/11");
      }
    
    private static void deleteAllRestaurant(){
    	 System.out.println("Testing deleteAllRestaurant API----------");
         RestTemplate restTemplate = new RestTemplate();
         restTemplate.delete(REST_SERVICE_URI+"/db/bistros/");
     }

    
    public static void main(String args[]){
    	getAllBistro();
    	getRestaurant();
    	addBistro();
    	getAllBistro();
    	deleteRestaurant();
    	getAllBistro();
    	//deleteAllRestaurant();
    	//getAllBistro();
    }
}
