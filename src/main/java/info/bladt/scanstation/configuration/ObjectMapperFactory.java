package info.bladt.scanstation.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperFactory {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
