package org.cloudfoundry.samples.music.config.data;

import com.mongodb.MongoURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

@Configuration
@Profile("mongodb-local")
public class MongoLocalConfig {

//    @Bean
//    public MongoDbFactory mongoDbFactory() {
//        try {
//            return new SimpleMongoDbFactory(new MongoClient(), "music");
//        } catch (UnknownHostException e) {
//            throw new RuntimeException("Error creating MongoDbFactory: " + e);
//        }
//    }
    @Bean
    public MongoDbFactory mongoDbFactory() throws UnknownHostException {
        String mongoUrl = "nosqldb";
        int mongoPort = 27017;
        String mongoDatabase = "springmusic";

        MongoURI mongoUri
                = new MongoURI("mongodb://" + mongoUrl + ":"
                        + mongoPort + "/" + mongoDatabase);
        try {
            return new SimpleMongoDbFactory(mongoUri);
        } catch (UnknownHostException e) {
            throw new RuntimeException("Error creating SimpleMongoDbFactory: " + e);
        }
    }

}
