package com.kafka.streams;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.TopologyTestDriver;
import org.apache.kafka.streams.test.ConsumerRecordFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import com.kafka.streams.config.KafkaConfiguration;
import com.kafka.streams.config.TopologyConfiguration;

public class TopologyConfigurationTest {

    TopologyTestDriver topologyTestDriver;

    @Before
    public void setUp() {
        KafkaStreamsConfiguration config = new KafkaConfiguration().getStreamsConfig();
        
        // create topology to handle stream of users
        StreamsBuilder streamBuilder = new StreamsBuilder();
        Topology topology = new TopologyConfiguration().createTopology(streamBuilder);
        
        System.out.println("TOPOLOGY" + topology);

        // set up dummy properties needed for test diver
        topologyTestDriver = new TopologyTestDriver(
                topology, config.asProperties());

        topologyTestDriver = new TopologyTestDriver(
                topology, config.asProperties());
    }

    public void processValue(String key, String value) {
        ConsumerRecordFactory<String, String> factory = new ConsumerRecordFactory<>(Serdes.String().serializer(),
                Serdes.String().serializer());
        topologyTestDriver.pipeInput(factory.create("Topic1", key, value));
    }

    @Test
    public void testTopology() {
        processValue("a", "hello, world, This is the first message");
        ProducerRecord<String, String> topic2 = topologyTestDriver.readOutput("Topic2", Serdes.String().deserializer(), Serdes.String().deserializer());
        assertEquals("a",topic2.key());
        assertEquals("HELLO, WORLD, THIS IS THE FIRST MESSAGE", topic2.value());
    }
}