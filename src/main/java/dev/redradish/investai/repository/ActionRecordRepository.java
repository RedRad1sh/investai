package dev.redradish.investai.repository;

import dev.redradish.investai.jpa.ActionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author nkucherenko
 */
@Repository
public interface ActionRecordRepository extends JpaRepository<ActionRecord, UUID> {
}
