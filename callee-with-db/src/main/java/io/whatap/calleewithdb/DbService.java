package io.whatap.calleewithdb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import java.util.Random;


@Service
public class DbService {
    @PersistenceContext
    private EntityManager entityManager;

    public Object sleep(){
        int randomInt = randomManipulateBigNum(1, 30);
        return entityManager.createNativeQuery("select pg_sleep("+ randomInt +")")
                .getSingleResult();
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
