# divide-and-Kafka

`divide-and-Kafka` demonstrates the use of Kafka Streams in reading a message from a Kafka Topic, converting that to uppercase, and finally, publishing to a secondary Kafka Topic.

## Tech Stack
```
Java 1.8 or later
Spring Tool Suite
Spring-Kafka
kafka-streams
Docker version 17+
```

## Usage

Check to ensure you are not behind a proxy. Otherwise, start up Kafka by going to the base folder and running:
```
docker-compose up -d
```

Start application
```
mvn clean install
java -jar target/kafkastreams.jar
```

Or run this class from choice IDE
```
KafkaStreamsApplication.java
```

## Relevant Resources

Kafka Streams provides an extremely simple, elastic deployment model for scaling out streaming applications. You may elect to deploy your application many times and the instances will automatically share the work amongst themselves.

Fall into a deeper rabbit hole here: https://docs.confluent.io/current/streams/introduction.html
