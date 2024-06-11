package io.whatap.caller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> call() {
        String result = restTemplate.getForObject(calleeUrl + "/callee/health", String.class);
        log.info("caller call, time={}, result={}", System.currentTimeMillis(), result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/log")
    public ResponseEntity<?> log() {
        String result = restTemplate.postForObject(calleeUrl + "/callee/log", null, String.class);
        log.info("caller call log create, time={}, result={}", System.currentTimeMillis(), result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/calculate/all")
    public ResponseEntity<?> calculateAll() {
        String result = restTemplate.getForObject(calleeUrl + "/callee/calculate/all", String.class);
        log.info("caller calculate all, time={}, result={}", System.currentTimeMillis(), result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/db/postgresql/sleep")
    public ResponseEntity<?> dbPostgresqlSleep() {
        restTemplate.getForObject(calleeUrl + "/callee/db/postgresql/sleep", String.class);
        log.info("caller db postgresql sleep, time={}", System.currentTimeMillis());
        return ResponseEntity.ok().build();
    }
}
