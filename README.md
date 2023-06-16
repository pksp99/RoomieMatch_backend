# RoomieMatch Backend

API for managing user information and groups for a Roommate Finder application. This is the backend for [RoomieMatch](https://github.com/pksp99/RoomieMatch)

## API Documentation

Explore the [API documentation](https://pksp99.github.io/RoomieMatch_backend/) to know more about the endpoints and schemas.

## Getting Started

To start the Roommate Finder API, follow these steps:

1. Ensure you have Java and Maven installed on your system.
2. Clone this repository: `git clone https://github.com/pksp99/RoomieMatch_backend.git`
3. Navigate to the project directory: `cd repository`
4. Build the application: `mvn clean package`
5. Run the application: `java -jar target/RoomieMatch_backend-0.0.1-SNAPSHOT.jar`
6. The API will start running on port 8080.

## Usage

Once the server is running, you can start making API requests using the following endpoints:

- `GET /users`: Retrieve a list of users.
- `POST /users`: Create a new user.
- `GET /users/{id}`: Retrieve a specific user by ID.
- `PUT /users/{id}`: Update a specific user.
- `DELETE /users/{id}`: Delete a specific user.

- `POST /like/{groupId}`: Like a group with specified groupId, for user with x-userId
- `POST /dislike/{groupId}`: Dislike a group with specified groupId, for user with x-userId
- `GET /like`: Get all likes for user with x-userId

- `POST /makegroup`: Merge two groups with group_id_1 and group_id_2
- `GET /mygroup`: Get group for user with x-userId


