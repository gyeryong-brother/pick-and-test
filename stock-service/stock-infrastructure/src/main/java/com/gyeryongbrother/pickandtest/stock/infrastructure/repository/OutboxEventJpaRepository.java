package com.gyeryongbrother.pickandtest.stock.infrastructure.repository;

import com.gyeryongbrother.pickandtest.stock.infrastructure.entity.OutboxEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OutboxEventJpaRepository extends JpaRepository<OutboxEventEntity, Long> {

    @Query("SELECT e FROM OutboxEventEntity e WHERE e.aggregateType = :aggregateType ORDER BY e.createdAt ASC")
    List<OutboxEventEntity> findUnpublishedEventsWithLock(@Param("aggregateType") String aggregateType);
}
