package groonvail.example.bitesizedlibrary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class UtilConfiguration {
    @Bean
    public Random random() {
        return new Random();
    }
}
