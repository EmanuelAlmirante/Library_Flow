# Library Flow



### Necessary Tools:

You will need to install **Intellij IDEA** (https://www.jetbrains.com/idea/download/#section=linux), **Docker** (https://docs.docker.com/get-docker/), **Postman** (https://www.postman.com/downloads/), and **Java 17** (Windows - https://docs.oracle.com/en/java/javase/17/install/installation-jdk-microsoft-windows-platforms.html | Ubuntu 20.04 - https://www.linuxcapable.com/how-to-install-java-17-lts-jdk-17-on-ubuntu-20-04/ | MacOS - https://java.tutorials24x7.com/blog/how-to-install-java-17-on-mac). 

### Tech Stack:

- Java 17
- Spring Boot
- OpenAPI
- H2 Database (in-memory)
- JUnit
- Hamcrest
- Mockito

### Setup:

- Clone project to a folder (_**git clone https://github.com/EmanuelAlmirante/Library_Flow/.git**_)
- Open terminal in the project folder
- Run the application with:
  - _mvn clean install_
  - _mvn spring-boot:run_
- Test the application with:
  - _mvn test_ -> run all tests
  - _mvn -Dtest=TestClass test_ -> run a single test class
  - _mvn -Dtest=TestClass1,TestClass2 test_ -> run multiple test classes
- Package the application with _mvn package_
- Test using Postman

### To Use With Docker:
  - Install Docker on your machine
  - Launch Docker
  - Run the command _sudo systemctl status docker_ to confirm Docker is running
  - Open terminal in the project folder
  - Run the command _docker-compose up_
  - Test using Postman
  
### Endpoints:

The documentation of this API can be found at _ http://localhost:8080/swagger-ui.html_ or _http://localhost:8080/library-docs_ (**Note: you need to initialize the application to access this link**).
