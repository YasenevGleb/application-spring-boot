# app-spring-boot
## make sure that you have :
  - java 15
  - mvn 3.6.3 
    ### point to run app  
    - git clone this one https://github.com/YasenevGleb/app-spring-boot.git
    - do some simple commands 
      1. go to your package
      2. mvn clean
      3. mvn compile
      4. mvn package 
    - after all you will have .jar file 
    - run app with this command: java jar yourJar.jar 
## Let`s open Postman and create first POST request to login with admin_permissions and get our Bearer token
   1. curl -XPOST -H "Content-type: application/json" -d '{
"username" : "admin",
"password" : "34bb"
}' 'localhost:8080/login'
  2. Take bearer from headers , for each request make sure that you have AuthorizationHeader(Authorization) and TokenPrefix (Bearer token)
## To test API you can use curls:
   - To save new User: curl -XPOST -H 'Authorization: Bearer' -d '{
    "name" : "name",
    "age" : "age" }' 'localhost:8080/users'
 
  - To save new Article: curl -XPOST -H 'Authorization: Bearer' -d '{
    "text" : "text",
    "colorOfArticle" : "colorOfArticle"
    }' 'localhost:8080/articles?userID={}'
  - To get user by count of articles: curl -XGET -H 'Authorization: Bearer' 'localhost:8080/users/{count}/articles'
  - To get users by color of articles: curl -XGET -H 'Authorization: Bearer' 'localhost:8080/users/articles?color={color}'
  - To get users by age: curl -XGET -H 'Authorization: Bearer' 'localhost:8080/users?age={age}'
