# joke_application
The Joke Application is an API that serves up random jokes upon request.  The response contains three fields:
+ “**type**”: The type of the joke (e.g., “general,” “programming,” “knock-knock,” etc.).
+ “**setup**”: The setup or beginning of the joke.
+ “**punchline**”: The humorous conclusion of the joke.
# Technologies Used
+ **Java Spring Boot**: The backend framework for building the API.
+ **Maven**: Dependency management and build tool.
# API Endpoints
## Post New Joke
+ **Endpoint**: /api/v1/jokes/post
+ **Method**: POST
+ **Request Body Format**: JSON
+ **Response Format**: JSON
+ **Example Request**:
```json
 { 
  "type": "programming",
  "setup": "Why do programmers prefer dark mode?",
  "punchline": "Because light attracts bugs!"
  }
```
## Get All Jokes
+ **Endpoint**: /api/v1/jokes
+ **Method**: GET
+ **Response Format**: JSON
+ **Example Response**:
```json
[
  {
    "type": "general",
    "setup": "What did the fish say when it hit the wall?",
    "punchline": "Dam."
  },
  {
    "type": "general",
    "setup": "How do you make a tissue dance?",
    "punchline": "You put a little boogie on it."
  },
  {
    "type": "general",
    "setup": "What's Forrest Gump's password?",
    "punchline": "1Forrest1"
  }
]
```
## Get Random Joke
+ **Endpoint**: /api/v1/jokes/random
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
## Get Random Joke by the type
+ **Endpoint**: /api/v1/jokes/random/{type}
+ **Method**: GET
+ **Response Format**: JSON
+ **Example Response**:
```json
 { 
  "type": "general",
  "setup": "What do you call a laughing motorcycle?",
  "punchline": "A Yamahahahaha."
  }
```
## Update The Joke By The Setup
+ **Endpoint**: /api/v1/jokes/update-joke
+ **Method**: PUT
+ **Request Body Format**: JSON
+ **Response Format**: JSON
+ **Example Request**:
```json
 { 
  "type": "general",
  "setup": "What do you call a laughing motorcycle?",
  "punchline": "A Yamahahahaha."
  }
```
## Delete The Joke
+ **Endpoint**: /api/v1/jokes/delete-joke
+ **Method**: Delete
+ **Request Body Format**: JSON
+ **Example Request**:
```json
 { 
  "type": "general",
  "setup": "What do you call a laughing motorcycle?",
  "punchline": "A Yamahahahaha."
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
5. Access the API at ```http://localhost:8080/api/v1/jokes/random```.
# Deployment
Deploy the application to your preferred hosting platform or cloud service.

# Contributing
Contributions are welcome! Feel free to submit pull requests or open issues.