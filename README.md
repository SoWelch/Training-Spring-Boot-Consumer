## About this project

This project is being developed for training purposes.  The overall stack will include:  
    
    - Spring-boot  
    - Kafka
    - Postgres
    - Hibernate
    - SLF4J
    - Mockito
    - JUnit
    
This project includes the Database and Kafka consumer pieces, the Producer and general website pieces are included in this 
[Separate Project](https://github.com/SoWelch/Training-Spring-Boot).  

## How this was built

This was built using multiple tutorials:  

    Basic spring boot setup:  
        https://spring.io/guides/gs/spring-boot/  
            
    Setup of junit in project:  
        https://spring.io/guides/gs/testing-web/
        
    Setup for Thymeleaf:
        https://www.callicoder.com/spring-boot-maven-thymeleaf-tutorial/
      
<br />

## Here's some helpful information

When running this app, you can check any problems or just get more info by opening: 
    
    file:///Users/YOURNAME/git/training-spring-boot/build/reports/tests/test/index.html
    
<br />
To start the app:

    ./gradlew clean build ; java -jar build/libs/training-spring-boot-consumer-0.1.0.jar

<br />
Using Swagger:

    Once you have the application running, go to: http://localhost:8080/swagger-ui.html#/
