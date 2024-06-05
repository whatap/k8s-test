package io.whatap.callee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalleeService {
    private final RestTemplate restTemplate;
    @Value("${callee-with-db-url}")
    private String calleeWithDbUrl;
    public String calleeCreateLog() {
        log.info("calleeCreateLog called");
        return restTemplate.postForObject(calleeWithDbUrl+"/log/create", null, String.class);
    }
}
