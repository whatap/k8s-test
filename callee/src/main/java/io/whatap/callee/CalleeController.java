package io.whatap.callee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/callee")
public class CalleeController {
    private final CalleeService calleeService;

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck() {
        log.info("callee called, time={}", System.currentTimeMillis());
        return ResponseEntity.ok("Callee service is alive.");
    }

    @PostMapping("/log")
    public ResponseEntity<?> logCall() {
        log.info("callee called logCall, time={}", System.currentTimeMillis());
        String result = calleeService.calleeCreateLog();
        return ResponseEntity.ok(result);
    }
}
