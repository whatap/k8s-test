package io.whatap.oom;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemoryResetScheduler {
    private final OomExecutor oomExecutor;
    @Scheduled(cron = "${app.reset.cron}")
    public void resetMemory() {
        oomExecutor.reset();
    }
}
