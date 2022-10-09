# Instructions on how to compile and run the project
    mvn clean install
    mvn spring-boot:run

# How to run unit test in the project
    mvn test

# How to run individual text files

    mvn spring-boot:run
    
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=positive/scores.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=positive/positive/perfect.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/empty.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/extra-score.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/free-text.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/invalid-score.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/negative.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/all-fauls.txt'
    curl --location --request POST 'http://localhost:8080/api/processScore?fileName=negative/all-zeros.txt'

    Another option to test the application is using Postman to call the REST method to validate files individually
    Exported Postman collection attached

# Details about why I chose coding a new project
    
    I chose to make a new project with Spring Boot, cause it helps in a lot of ways to code in a better and efficient way, such as the unit tests.
    Coding in a pure java project is kinda tricky and it doesn`t reproduce a real world project.
