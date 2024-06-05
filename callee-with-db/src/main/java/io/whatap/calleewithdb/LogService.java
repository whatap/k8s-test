package io.whatap.calleewithdb;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LogService {
    private final LoggerRepository loggerRepository;
    @Transactional
    public Log createLog() {
        Log log = new Log();
        log.setLog("log is created when "+ System.currentTimeMillis());
        return loggerRepository.save(log);
    }
}
