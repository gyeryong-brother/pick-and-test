package com.gyeryongbrother.pickandtest.stock.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class FavoriteStockEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long memberId;

    @ManyToOne
    private StockEntity stock;
}
