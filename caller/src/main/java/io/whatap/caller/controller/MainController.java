package io.whatap.caller.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Controller
public class MainController {
    private final RestTemplate restTemplate;
    @Value("${callee.url}")
    private String calleeUrl;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/health")
    public String health() {
        String result = restTemplate.getForObject(calleeUrl + "/callee/health", String.class);
        return "index";
    }
    @GetMapping("/log")
    public String createLog() {
        String result = restTemplate.postForObject(calleeUrl + "/callee/log", null, String.class);
        return "index";
    }
    @GetMapping("/calculate/all")
    public String cpuLoad() {
        String result = restTemplate.getForObject(calleeUrl + "/callee/calculate/all", String.class);
        return "index";
    }
    @GetMapping("/db/postgresql/sleep")
    public String dbSleep() {
        restTemplate.getForObject(calleeUrl + "/callee/db/postgresql/sleep", String.class);
        return "index";
    }






}
