package com.kafka.streams.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.streams.StreamsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams // integrate the Kafka Stream API with Spring Framework
public class KafkaConfiguration {
    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME) // enable default Kafka Streams components
    public KafkaStreamsConfiguration getStreamsConfig() {
      Map<String, Object> properties = new HashMap<>();
      // set key parameters
      properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafkastreams");
      // props.put(StreamsConfig.APPLICATION_ID_CONFIG, "filter"); 
      properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
      properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, 4);
      // properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
      // properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
      return new KafkaStreamsConfiguration(properties);

    }

    @Bean
    public NewTopic fooTopic() {
      return new NewTopic("Topic1", 10, (short) 1);
    }

    @Bean
    public NewTopic barTopic() {
      return new NewTopic("Topic2", 10, (short) 1);
    }
}
