package io.whatap.calleewithdb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/db")
public class DbController {
    private final DbService dbService;

    @GetMapping("/postgresql/sleep")
    public ResponseEntity<?> pgSleep() {
        Object result = dbService.sleep();
        log.info("dbService.sleep() returned " + result);
        return ResponseEntity.ok(result);
    }
}
