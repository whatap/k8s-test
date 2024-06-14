package io.whatap.callee;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Service
public class CalleeService {
    private final RestTemplate restTemplate;
    @Value("${callee-with-db-url}")
    private String calleeWithDbUrl;
    public String calleeCreateLog() {
        log.info("calleeCreateLog called");
        return restTemplate.postForObject(calleeWithDbUrl+"/log/create", null, String.class);
    }

    public String calleeSleepDb() {
        log.info("db sleep called");
        return restTemplate.getForObject(calleeWithDbUrl+"/db/postgresql/sleep", String.class);
    }

    /*
        cpu 부하를 올리는 메서드
     */
    public void calculateAll() {
        busy();
    }

    public void busy() {
        long end = System.currentTimeMillis() + randomManipulateBigNum(1000, 30000);
        while (System.currentTimeMillis() < end) {
            // 바쁜 대기 루프 - 소수 계산
            for (int i = 2; i < 20000; i++) {
                isPrime(i);
            }
        }
    }

    public boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    public int randomManipulateBigNum(int min, int max) {
        Random random = new Random();
        double randomDouble = random.nextDouble();
        double skewedDouble = Math.pow(randomDouble, 0.5);  // You can experiment with this value
        int skewedRandomNumber = min + (int)((max - min + 1) * skewedDouble);
        if (skewedRandomNumber > max) {
            skewedRandomNumber = max;
        } else if (skewedRandomNumber < min) {
            skewedRandomNumber = min;
        }
        return skewedRandomNumber;
    }
}
