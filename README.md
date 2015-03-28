TFG
============


* Spring MVC with Content Negotiation
* Spring Data JPA/Hibernate persistence over HSQL and Heroku PostgreSQL
* Unit Testing
* Spring Cucumber acceptance tests
* Simple CORS Filter
* Travis Continuous Integration
* ...

To run locally, first build WAR package:
```
mvn package
```

Then run embedded Tomcat server:
```
mvn exec:exec
```

The application will be available at http://localhost:8080/greetings
