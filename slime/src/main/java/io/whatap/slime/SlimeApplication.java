package io.whatap.slime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SlimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlimeApplication.class, args);
    }

}
