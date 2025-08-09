package com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "stock_minute_price")
public class StockMinutePriceEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long stockId;
    private LocalDateTime dateTime;
    private BigDecimal price;

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public LocalDateTime dateTime() {
        return dateTime;
    }

    public BigDecimal price() {
        return price;
    }
}
