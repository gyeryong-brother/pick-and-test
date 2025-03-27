package com.gyeryongbrother.pickandtest.stockprice.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.stockprice.domain.core.entity.StockPrice;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "stock_price")
public class StockPriceEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long stockId;
    private LocalDate date;
    private BigDecimal price;

    public StockPrice toDomain() {
        return new StockPrice(id, stockId, date, price);
    }

    public Long id() {
        return id;
    }

    public Long stockId() {
        return stockId;
    }

    public LocalDate date() {
        return date;
    }

    public BigDecimal price() {
        return price;
    }
}
