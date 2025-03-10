package io.whatap.oom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OomApplication {

    public static void main(String[] args) {
        SpringApplication.run(OomApplication.class, args);
    }

}
