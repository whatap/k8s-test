package io.whatap.caller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api")
public class CallerController {
    private final RestTemplate restTemplate;

    @Value("${callee.url}")
    private String calleeUrl;

    @GetMapping("/call")
    public void call() {
        String result = restTemplate.getForObject(calleeUrl + "/callee/health", String.class);
        log.info("caller call, time={}, result={}", System.currentTimeMillis(), result);
    }

    @GetMapping("/log")
    public void log() {
        String result = restTemplate.postForObject(calleeUrl + "/callee/log", null, String.class);
        log.info("caller call log create, time={}, result={}", System.currentTimeMillis(), result);
    }
}
