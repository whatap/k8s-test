package io.whatap.oom;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OomExecutor {
    @Value("${app.memory.limit}")
    private Integer limitMemory;

    @Value("${app.memory.increase}")
    private Integer increaseMemory;

    private static final List<byte[]> memory = new ArrayList<>();

    public void execute() {
        if (limitMemory != null && limitMemory > 0 && limitMemory >= memory.size() * increaseMemory) {
            increaseMemory();
        }
        else if (limitMemory == null || limitMemory <= 0){
            increaseMemory();
        }
    }

    public void reset() {
        memory.clear();
    }

    private void increaseMemory() {
        memory.add(new byte[1024*1024*increaseMemory]);
    }
}
