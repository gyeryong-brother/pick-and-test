package com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "local_stocks")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LocalStockEntity {

    @Id
    private Long stockId;

    @Column(nullable = false, unique = true)
    private String symbol;

    @Column(nullable = false)
    private LocalDateTime syncedAt;

    public LocalStockEntity(Long stockId, String symbol) {
        this.stockId = stockId;
        this.symbol = symbol;
        this.syncedAt = LocalDateTime.now();
    }
}
