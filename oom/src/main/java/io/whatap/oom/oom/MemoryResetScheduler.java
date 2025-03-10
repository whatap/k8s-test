package io.whatap.oom.oom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemoryResetScheduler {
    private final OutOfMemoryExecutor outOfMemoryExecutor;
    @Scheduled(cron = "${app.reset.cron}")
    public void resetMemory() {
        outOfMemoryExecutor.reset();
    }
}
