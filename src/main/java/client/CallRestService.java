package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import Model.Restaurant;

@Component
public class CallRestService implements CommandLineRunner {
	
	public static void callRestService() {
		RestTemplate restTemplate = new RestTemplate();
		//Restaurant restaurant = restTemplate.getForObject("http://localhost:8888/restaurants/2", Restaurant.class);
		//System.out.println("Restaurant's Name is "+ restaurant.getName() );
	}

	@Override
	public void run(String... args) throws Exception {
		callRestService();
	}

	

}