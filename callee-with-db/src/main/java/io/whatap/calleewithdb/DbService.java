package io.whatap.calleewithdb;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;


@Service
public class DbService {
    @PersistenceContext
    private EntityManager entityManager;

    public Object sleep(){
        return entityManager.createNativeQuery("select pg_sleep(20)")
                .getSingleResult();
    }
}
