package io.whatap.oom.oom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ConditionalOnMissingBean(LocalMemoryOomExecutor.class)
@Component
public class StaticMemoryOomExecutor implements OutOfMemoryExecutor {
    @Value("${app.memory.limit}")
    private Integer limitMemory;

    @Value("${app.memory.increase}")
    private Integer increaseMemory;

    private static final List<byte[]> memory = new ArrayList<>();

    @PostConstruct
    public void init() {
        log.info("Static Memory OOM executor initialized");
    }

    @Override
    public void execute() {
        if (limitMemory != null && limitMemory > 0 && limitMemory >= memory.size() * increaseMemory) {
            increaseMemory();
        }
        else if (limitMemory == null || limitMemory <= 0){
            increaseMemory();
        }
    }

    @Override
    public void reset() {
        memory.clear();
    }

    private void increaseMemory() {
        memory.add(new byte[1024*1024*increaseMemory]);
    }
}
