# Kafka ALFI Demo

This application demonstrates application level fault injection (ALFI) on an [Apache Kafka](http://kafka.apache.org/) application using [Gremlin](https://gremlin.com). It uses the Gremlin ALFI client to inject attacks into Kafka producer and consumer calls.

The application creates a REST API endpoint that records the IP address and name of each client that connects to it. It publishes this data to a Kafka topic, and a consumer records each event to a log file. There are fault injection points in both the producer and consumer classes.

For a more in-depth look, read the Gremlin tutorial on [How to run application-level fault injection attacks on Apache Kafka](https://www.gremlin.com/community/tutorials/).

## Requirements

To run this application, you'll need:

- Java 11+
- An [Apache Kafka](https://kafka.apache.org) cluster
- A [Gremlin account](https://app.gremlin.com/signup) with access to application attacks
- Your [Gremlin team ID and secret](https://www.gremlin.com/docs/infrastructure-layer/authentication/#client-authentication-methods) (or certificates for signature-based authentication)

## Instructions

1. Clone the repository: 
    ```bash
    $ git clone https://github.com/8bitbuddhist/kafka-alfi-demo.git
    $ cd kafka-alfi-demo
    ```
2. Add your Gremlin account credentials to `src/main/resources/gremlin.properties`
3. Configure your Kafka brokers in `src/main/resources/application.yml`
4. Run the application with Gradle: `$ ./gradlew bootRun`
5. Send a `POST` request to the REST API at `http://localhost:9000/kafka/publish`, or run `./generate-data.sh` to generate data. 
6. [Log into your Gremlin account](https://app.gremlin.com), go to Attacks > Application, and create a new attack.
    - Under "ApplicationQuery" and "TrafficQuery", select "Custom Application Type" and enter `KafkaALFIDemo` for the name.
    - To run an attack on the producer, enter this into the Traffic Query > Custom Value field:
      ```
      key: service
      value: producer
      ```
   - To run an attack on the consumer, enter this into the Traffic Query > Custom Value field:
      ```
      key: service
      value: consumer
      ```
 7. Set your attack parameters, then click "Unleash Gremlin."

## More Information

To learn more about running ALFI attacks using Gremlin, read the [Gremlin documentation](https://www.gremlin.com/docs/application-layer/overview/). 