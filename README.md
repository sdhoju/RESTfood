RESTfood Documentation   
      Let’s get this running.    To run this there are few environments set up and few software.   
      Environments:   
      1. Java 8   
      2. MySQL   
      3. Tomcat   
      4. Developed in windows OS   
      Software:   
      1. Spring tool Suit of instance of that in Eclipse JEE   
      2. Tomcat    
      3. MySQL      
      4. Git    
      5. Postman   
      Once all the Environment and Software are set up follow these steps.   
      1. Download the project from https://github.com/sdhoju/RESTfood.git    
      2. Set up the MySQL database. Once the database and user is set up go to the application.properties in “RESTfood\src\main\resources” directory.  spring.datasource.url connects the data base with the Program. Chang the database name and username and password from your local MySQL. (Note.  On the first run change the spring.jpa.hibernate.ddl-auto = create to create the tables. But change it back to update so that we don’t lose all the data every run.)  in RESTfood/data there are 3 csv files to populate the database. I used excel to populate (in opened excel file Data>MySQL)   
      3. Once the database is set up it’s ready to be run. Open the whole RESTfood directory as workspace in Eclipse JEE with spring instance or from STS.   
      4. To Run the server/program, Right clicked the directory and Run as >  Spring boot application. (go to others and search for it if it is not present.) If Eclipse ask for the java application select the  com.example.demo RestFoodApplication.java. The port is set to 8888 so to see it go to http://localhost:8888.  (Note: there are commented out functions in RestaurantController that can be run in Memory)
      5 To get to the Api Documentation. Clicked in API documentation in  http://localhost:8888. Or go to http://localhost:8888/jsondoc-ui.html# and type as http://localhost:8888/jsondoc the url. 
      6 There are few unit tests. After you have running server go to RESTfood\src\test\java and run the RestFoodApplicationTests.java as junit. There is also RestFoddTestClient, a client base test in RESTfood\src\main\java under client pacakage.  
If there is any complication while running the program. Reach me at samee.dhoju@gmail.com. 
