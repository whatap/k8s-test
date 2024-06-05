package io.whatap.calleewithdb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/log")
public class LogController {
    private final LogService logService;

    @PostMapping("/create")
    public ResponseEntity<?> createLog() {
        log.info("create log controller entered, time={}", System.currentTimeMillis());
        Log log = logService.createLog();
        return ResponseEntity.ok(log);
    }
}
