package io.whatap.oom.oom;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ConditionalOnProperty(name = "app.memory.type", havingValue = "local")
@Component
public class LocalMemoryOomExecutor implements OutOfMemoryExecutor{
    @Value("${app.memory.limit}")
    private Integer limitMemory;

    @Value("${app.memory.input-huge-memory}")
    private Integer hugeMemory;

    @PostConstruct
    void init() {
        log.info("Local Memory OOM executor initialized");
    }

    @Override
    public void execute() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();

        long used = heapMemoryUsage.getUsed();       // 사용 중인 힙 메모리 (bytes)

        if(used < limitMemory * 1024 * 1024){
            List<byte[]> bytes = new ArrayList<>();
            bytes.add(new byte[1024*1024*hugeMemory]);
        }
    }

    @Override
    public void reset() {

    }
}
