package com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.math.BigDecimal;

public class PortfolioStockFixture {

    //애플:1, 마이크로소프트:2, 엔비디아:3, 테슬라:4, 구글:5, 메타:6, 아마존:7

    public static PortfolioStock apple(){
        return new PortfolioStock(null,null,1L, BigDecimal.valueOf(0.5));
    }

    public static PortfolioStock microsoft(){
        return new PortfolioStock(null,null,2L, BigDecimal.valueOf(0.5));
    }

    public static PortfolioStock nvidia(){
        return new PortfolioStock(null,null,3L, BigDecimal.valueOf(0.5));
    }

    public static PortfolioStock tesla(){
        return new PortfolioStock(null,null,4L, BigDecimal.valueOf(0.5));
    }

    public static PortfolioStock google(){
        return new PortfolioStock(null,null,5L, BigDecimal.valueOf(0.4));
    }

    public static PortfolioStock meta(){
        return new PortfolioStock(null,null,6L, BigDecimal.valueOf(0.3));
    }

    public static PortfolioStock amazon(){
        return new PortfolioStock(null,null,7L, BigDecimal.valueOf(0.3));
    }
}
