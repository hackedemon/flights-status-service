# Info
Template project for Java based non-reactive RESTful API with DB based microservices in Docker.

# Pre-requisites

* [Docker](https://www.docker.com/) is installed and running.
  * Make sure that docker value is added to path.
* Elasticsearch and Kibana credentials are available.
* Make sure [JAVA 17 LTS](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) is installed.
    * Make sure that your IDE also point to version 17 to avoid issues.
* Make sure [Maven](https://maven.apache.org/download.cgi) is installed.

# Running the application
To run the application, follow the below steps:

1. Open the code in IDE and update Elasticsearch credentials in `application.yaml` file.
2. Open terminal in project root directory.
3. Run below commands:
   ````
   mvn clean compile jib:dockerBuild
   docker run -d -p 6789:6789 flights-status-service
   ````
4. In browser, open `localhost:6789` to see API documentation.

# Development guide
To make changes in the template, follow the below steps:

* Use jira ticket reference to create a branch from master.
* Clone new branch in your favourite IDE. 
* Update the version of in `pom.xml` file before raising PR.