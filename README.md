# RESTfood
## Author  
Sameer Dhoju samee.dhoju@gmail.com

## Requirements:   
1.  Java 8   
2.  MySQL   
3.  Tomcat   

## Software:   
1.  Spring tool Suit of instance of that in Eclipse JEE   
2.  Tomcat    
3.  MySQL      
4.  Git    
5.  Postman
   
## Getting Started
1. Download the project    
2. Set up the MySQL database. Once the database and user is set up go to the application.properties in “RESTfood\src\main\resources” directory.  spring.datasource.url connects the data base with the Program.  To create the tables you must have
```
spring.jpa.hibernate.ddl-auto = create
```
Make the following changes once the tables are created.
```
spring.jpa.hibernate.ddl-auto = update
```
 
## Use
### Server
To Run the server/program, Right clicked the directory and Run as >  Spring boot application. The port is set to 8888 go to http://localhost:8888.  (To run without setting up the database: commented out functions in RestaurantController)

### API Documentation
Clicked in API documentation in  http://localhost:8888. Or go to http://localhost:8888/jsondoc-ui.html# and type the url ```http://localhost:8888/jsondoc```

### Testing
Run the RestFoodApplicationTests.java as junit. 
You can also test client side by running RestFoddTestClient  
