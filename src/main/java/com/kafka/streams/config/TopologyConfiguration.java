package com.kafka.streams.config;

import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;

import org.apache.kafka.common.serialization.Serdes;
import static org.apache.kafka.common.serialization.Serdes.Long;
import static org.apache.kafka.common.serialization.Serdes.String;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/***
 * The class TopologyConfiguration creates a topology using StreamsBuilder, 
 * defines the transformations such as piping in topics, uppercase operation
 */
@Configuration
public class TopologyConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(TopologyConfiguration.class);

    @Bean
    public Topology createTopology(StreamsBuilder streamsBuilder) {

        LOG.info("Creating stream topology");

        // add source stream to topology, serialize records written to a Kafka topic
        KStream<String, String> foo = streamsBuilder.stream("Topic1",
                                            Consumed.with(Serdes.String(), Serdes.String()))
                                                    .mapValues((ValueMapper<String, String>) String::toUpperCase); // convert message contents to uppercase

        foo.print(Printed.toSysOut());
        foo.to("Topic2", Produced.with(Serdes.String(), Serdes.String())); // publish results to a topic2

        return streamsBuilder.build(); // return stream
    }
}