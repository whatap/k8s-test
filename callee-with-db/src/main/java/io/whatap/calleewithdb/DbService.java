package io.whatap.calleewithdb;

import io.whatap.common.util.RandomUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import java.util.Random;


@Service
public class DbService {
    @PersistenceContext
    private EntityManager entityManager;

    public Object sleep(){
        int randomInt = RandomUtils.randomManipulateBigNum(1, 30);
        return entityManager.createNativeQuery("select pg_sleep("+ randomInt +")")
                .getSingleResult();
    }

}
