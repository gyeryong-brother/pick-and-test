package com.gyeryongbrother.pickandtest.portfolio.dataaccess.entity;

import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.Portfolio;
import com.gyeryongbrother.pickandtest.portfolio.domain.core.entity.PortfolioStock;
import java.util.List;
import javax.sound.sampled.Port;

public class PortfolioFixture {

    //포트폴리오1=애플 50% + 마이크로소프트 50% ->멤버 1의 포트폴리오
    //포트폴리오2=엔비디아 50% + 테슬라 50% ->멤버2의 포트폴리오
    //포트폴리오3=구글 40% +메타 30% + 아마존 30%->멤버 1의 포트폴리오

    public static Portfolio portfolio1(){
        List<PortfolioStock> portfolioStocks=List.of(
                PortfolioStockFixture.apple(),
                PortfolioStockFixture.microsoft()
        );
        return new Portfolio(null,1L,portfolioStocks);
    }

    public static Portfolio portfolio2(){
        List<PortfolioStock> portfolioStocks=List.of(
                PortfolioStockFixture.nvidia(),
                PortfolioStockFixture.tesla()
        );
        return new Portfolio(null,2L,portfolioStocks);
    }

    public static Portfolio portfolio3(){
        List<PortfolioStock> portfolioStocks=List.of(
                PortfolioStockFixture.google(),
                PortfolioStockFixture.meta(),
                PortfolioStockFixture.amazon()
        );
        return new Portfolio(null,1L,portfolioStocks);
    }

    public static Portfolio portfolio1Update(){
        List<PortfolioStock> portfolioStocks=List.of(
                PortfolioStockFixture.apple(),
                PortfolioStockFixture.tesla()
        );
        return new Portfolio(null,3L,portfolioStocks);
    }
}
