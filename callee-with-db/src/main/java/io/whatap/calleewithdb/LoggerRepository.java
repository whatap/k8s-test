package io.whatap.calleewithdb;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoggerRepository extends JpaRepository<Log, Long> {
}
