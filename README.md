# challenge

This is a simple REST API that fetches movie data from https://directa24-movies.wiremockapi.cloud/api/movies/search and returns Directors with a certain ammount of movies.
Some notable things about this api:

- As a personal preference, Java Records are used in place of Lombok. For the pourpuse of this challenge, Java Records accomplish the same thing as Lombok without resorting to any third party libraries. Lombok is more flexible and has benefits over java Records that can be seen in Model entities or some annotations, but in this case I did not see the need for a Data layer (models, repositories, embedded database, etc) or special annotations.
- As an added improvement or conveniance for this challenge, This API is deployed in https://challenge-50vw.onrender.com/. Much as a very simplified CI/CD pipeline, this provider allows for continuos deployments using Docker images and GitHub repositories, this way this api can be easily accesed without having to install it locally. **Please note that requests to this url can take up to 50~ seconds of delay as the application will spin down with inactivity and will re-allocate resources as new requests come in.**
- Swagger url: https://challenge-50vw.onrender.com/swagger-ui/index.html#
- For local configuration and deployment purposes, this app can be ran locally in 2 different ways (Please clone or download this repository first):
  - If docker is installed in local environment, app can be deployed in a Docker image by building and running the Dockerfile (don't forget to expose ports)
     ```
     docker build -t image-name .
     docker run -p 8080:8080 image-name
    ```
  - Import project to a IDE and build the maven project, then run the main class.
  - app endpoint on local env should be http://localhost:8080/directa24/challenge/api/directors
