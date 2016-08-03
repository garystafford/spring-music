package org.cloudfoundry.samples.music.config.data;

import com.mongodb.MongoClient;
import org.cloudfoundry.samples.music.repositories.mongodb.MongoAlbumRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@Profile("mongodb")
@PropertySource("classpath:config.properties")
@EnableMongoRepositories(basePackageClasses = {MongoAlbumRepository.class})
public class MongoConfig {

    @Value("${mongodb.url}")
    private String mongoUrl;

    @Value("${mongodb.port}")
    private Integer mongoPort;

    @Value("${mongodb.db}")
    private String mongoDatabase;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(mongoUrl, mongoPort);
        return new SimpleMongoDbFactory(mongoClient, mongoDatabase);
    }
}
