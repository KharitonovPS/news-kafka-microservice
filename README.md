This repository contains a Spring Boot project with two modules: a consumer module responsible for receiving messages, and a producer module responsible for sending messages. The project utilizes Apache Kafka for message communication.

## Consumer Module
Overview
The consumer module is responsible for receiving messages from an external source and persisting them into a PostgreSQL database.

### Repository
WikimediaDataRepo: JPA repository for handling the persistence of Wikimedia data.
### Entity
Wikimedia: JPA entity representing Wikimedia data.
### Service
WikimediaNewsConsumer: Kafka listener service that consumes messages, processes them, and persists the data.
## Producer Module
Overview
The producer module is responsible for fetching real-time Wikimedia events from an external source and sending them to a Kafka topic.

### Configuration
KafkaTopicConfig: Configures the Kafka topic for the project.
### Service
WikimediaNewsProducer: Fetches real-time Wikimedia events and sends them to the Kafka topic.
### Util
WikimediaNewsHandler: EventSource handler for processing Wikimedia events and forwarding them to the Kafka topic.
### Configuration
The project is configured to connect to a local Kafka broker and a PostgreSQL database. Kafka producer settings, such as bootstrap servers and topic name, are specified in the application.yaml file.

## Docker Compose Configuration
The included docker-compose.yaml file sets up a local environment with Zookeeper, Kafka broker, and PostgreSQL database services.

## Prerequisites
Docker
Docker Compose
## Setup
Run docker-compose up -d to start the services.
Ensure that the Kafka broker is available at localhost:9092.
## Usage
Start the consumer module to listen for and persist incoming messages.
Start the producer module to fetch and send real-time Wikimedia events.
Note: Adjust Kafka broker settings and other configurations in the application.yaml file as needed for your environment.

Feel free to explore and contribute to this project!
