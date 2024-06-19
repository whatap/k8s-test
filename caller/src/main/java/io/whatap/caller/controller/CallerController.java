package io.whatap.caller.controller;

import io.whatap.common.util.RandomUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/random")
    public ResponseEntity<?> random() throws InterruptedException {
        Thread.sleep(RandomUtils.randomManipulateBigNum(100, 20000));
        String result = restTemplate.getForObject(calleeUrl + "/callee/health", String.class);
        log.info("caller call health, time={}, result={}", System.currentTimeMillis(), result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/health")
    public ResponseEntity<?> call() throws InterruptedException {
        Thread.sleep(RandomUtils.randomManipulateBigNum(100, 5000));
        String result = restTemplate.getForObject(calleeUrl + "/callee/health", String.class);
        log.info("caller call health, time={}, result={}", System.currentTimeMillis(), result);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/log")
    public ResponseEntity<?> log() throws InterruptedException {
        Thread.sleep(RandomUtils.randomManipulateBigNum(100, 5000));
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
