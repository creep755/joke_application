# joke_application
The Joke Application is an API that serves up random jokes upon request. It fetches data from the open API provided by **official_joke_api** and returns a JSON response via a GET request. The response contains three fields:
+ “**type**”: The type of the joke (e.g., “general,” “programming,” “knock-knock,” etc.).
+ “**setup**”: The setup or beginning of the joke.
+ “**punchline**”: The humorous conclusion of the joke.
# Technologies Used
+ **Java Spring Boot**: The backend framework for building the API.
+ **Maven**: Dependency management and build tool.
# API Endpoints
## Get Random Joke
+ **Endpoint**: /api/v1/random
+ **Method**: GET
+ **Response Format**: JSON
+ **Example Response**:
```json
 { 
  "type": "programming",
  "setup": "Why do programmers prefer dark mode?",
  "punchline": "Because light attracts bugs!"
  }
```
# How to Run Locally
1. Clone this repository.
2. Make sure you have Java and Maven installed.
3. Navigate to the project directory.
4. Run the following command to start the application:
```
   mvn spring-boot:run
```
5. Access the API at ```http://localhost:8080/api/v1/random```.
# Deployment
Deploy the application to your preferred hosting platform or cloud service.

# Contributing
Contributions are welcome! Feel free to submit pull requests or open issues.