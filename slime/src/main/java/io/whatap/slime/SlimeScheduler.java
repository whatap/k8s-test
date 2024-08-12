package io.whatap.slime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SlimeScheduler {

    @Autowired
    private Environment env;

    private static Logger log = LoggerFactory.getLogger(SlimeScheduler.class);
    @Scheduled(cron = "0/30 * * * * *")
    public void slimeScheduled() {
        String appName = env.getProperty("spring.application.name");
        if(appName == null) {
            appName = "slime";
        }
        log.info("slime log!, What kind of slime is this? {}, now={}", appName, LocalDateTime.now());
    }
}
