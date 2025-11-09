package org.example.emotiwave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.example.emotiwave.infra.repository")
@EntityScan(basePackages = "org.example.emotiwave.domain.entities")
@SpringBootApplication
public class EmotiWaveApplication {



    public static void main(String[] args) {
        SpringApplication.run(EmotiWaveApplication.class, args);


    }

}
