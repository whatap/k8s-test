package io.whatap.oom;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OomThread extends Thread{
    String memo = System.getenv("OOM_MEMORY");
    String sleep = System.getenv("OOM_SLEEP");
    String increaseLimit = System.getenv("INCREASE_LIMIT");

    private static Logger log = LoggerFactory.getLogger(OomThread.class);
    private static List<byte[]> memory = new ArrayList<>();
    private OomThread() {
        start();
    }

    private static class OomThreadHolder {
        private static final OomThread INSTANCE = new OomThread();
    }

    public static OomThread getInstance() {
        return OomThreadHolder.INSTANCE;
    }

    //allocation direct memory ByteBuffer.allocateDirect()
    @Override
    public void run() {
        int mem = 10;
        int slep = 5000;
        int increaseLimitInt = -1;
        if (StringUtils.hasText(memo)) {
            mem = Integer.parseInt(memo);
        }
        if (StringUtils.hasText(sleep)) {
            slep = Integer.parseInt(sleep);
        }
        if (StringUtils.hasText(increaseLimit)) {
            increaseLimitInt = Integer.parseInt(increaseLimit);
        }
        while(true){
            try {
//                log.info("head memory used={}",);
                if (increaseLimitInt != -1 && increaseLimitInt >= memory.size() * mem) {
                    memory.add(new byte[1024*1024*mem]);
                }
                Thread.sleep(slep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
