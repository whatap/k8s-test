package io.whatap.caller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Component
public class CallerScheduler {
    private final RestTemplate restTemplate;

    @Value("${callee.url}")
    private String calleeUrl;

//    @Scheduled(cron="0/5 * * * * *")
    public void call() {
        String result = restTemplate.getForObject(calleeUrl+"/callee/health", String.class);
        log.info("caller call, time={}, result={}",System.currentTimeMillis(), result);
    }

//    @Scheduled(cron="0/7 * * * * *")
    public void log() {
        String result = restTemplate.postForObject(calleeUrl+"/callee/log",null, String.class);
        log.info("caller call log create, time={}, result={}",System.currentTimeMillis(), result);
    }
}
