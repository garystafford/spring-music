package org.cloudfoundry.samples.music.config.data;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@Profile("mongodb-local")
@PropertySource("classpath:config.properties")
public class MongoLocalConfig {

    @Value("${mongodb.url}")
    private String mongoUrl;

    @Value("${mongodb.port}")
    private Integer mongoPort;

    @Value("${mongodb.db}")
    private String mongoDatabase;

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(mongoUrl, mongoPort);
        return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
