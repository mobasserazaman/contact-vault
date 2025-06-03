# Contact Vault

A simple Java contact manager project using core Java, JDBC, and PostgreSQL database.

---

## Features

- Add, update, delete, and search contacts  
- Stores contacts with fields: ID, Name, Email, Phone, Birthday  
- Uses PostgreSQL for persistent data storage  
- Simple command-line interface (no GUI)  
- Built with Java 17 and Maven  

---

## Getting Started

### Prerequisites

- Java 17 or later installed  
- Maven installed  
- Docker (optional, for running PostgreSQL locally)  

### Setup PostgreSQL with Docker

To run the contact-postgres container : 
docker-compose up -d

Connect to PostgreSQL and create the contacts table:
- docker exec -it contact-postgres psql -U user -d contactdb
- CREATE TABLE contacts (
  id SERIAL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100),
  phone VARCHAR(20),
  birthday DATE
);

### Build the project with Maven
mvn clean package

### Run the application
java -jar target/contact-vault-1.0-SNAPSHOT-jar-with-dependencies.jar



