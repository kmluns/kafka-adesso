package com.datapp.chambre;

import com.datapp.chambre.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@EnableConfigurationProperties({
        FileStorageProperties.class
})
@SpringBootApplication
@EnableKafka
public class ChambreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChambreApplication.class, args);
    }
}
