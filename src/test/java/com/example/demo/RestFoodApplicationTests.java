package com.example.demo;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import java.util.LinkedHashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestFoodApplicationTests {

	@Test
	public void contextLoads() {
	}
	 public static final String REST_SERVICE_URI = "http://localhost:8888";
	 
	 @Test
	 public  void Bistro(){
	       Bistro bistro = new Bistro(12,"Test Bistro",123123123);
	       assertEquals(bistro.getId(), 12);
	       assertEquals(bistro.getName(), "Test Bistro");
	       assertEquals(bistro.getPhone(), 123123123);
	 
	 
	 }
	 @Test
	 public  void Menu(){
	       Menu menu = new Menu(12,"Test Bistro");
	       Menu menu1 = new Menu(12,"Test Bistro");
	       assertNotSame(menu, menu1);
	 }
	 @Test
	 public  void item(){
	       restItem item1 = new restItem(1,"Food",12);
	       restItem item2 = new restItem(2,"Food",12);
	       assertNotEquals(item1, item2);
	 }
}
