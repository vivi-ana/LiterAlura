# LiterAlura: book catalog


This project is the final challenge for Oracle Next Education program.
This application allows users to add and search for books and authors. It provides an interactive console interface for user interaction.

#### **Technologies Used**

- Java 17
- Spring Data JPA with JPQL
- Jackson Databind
- Collection Framework
- Java Stream API
- Database: PostgreSQL

## API Used
- [Gutendex API](https://gutendex.com/)  This API retrieves real-time book metadata.

## Setup
Create a `.env` file in the root directory of the application and add the following database connection configuration:

```properties
DB_HOST=
DB_NAME=
DB_USERNAME=
DB_PASSWORD=
```

**Ensure that the database connection details in the .env file are correctly filled in before running the application.**

* DB_HOST: It is the host or the IP address of the database server that the application will connect to in order to access the data. Typically, this could be something like localhost if the database is on the same machine as the application, or the IP address of the database server if it is on a remote server.
* DB_NAME: This value corresponds to the specific name of the database that contains the tables and data that the application needs to function correctly.
* DB_USERNAME: It is the username that the application will use to connect to the database.
* DB_PASSWORD: It is the password associated with the username specified above.

## Features 
- Search and add a book by title
- Display book and author details 
- Search for living authors by year
- Search for books by language

