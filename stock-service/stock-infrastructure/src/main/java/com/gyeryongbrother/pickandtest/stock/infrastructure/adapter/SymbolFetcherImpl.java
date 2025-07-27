package com.gyeryongbrother.pickandtest.stock.infrastructure.adapter;

import static com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureExceptionType.RESOURCE_READ_FAILED;

import com.gyeryongbrother.pickandtest.stock.domain.core.valueobject.StockExchange;
import com.gyeryongbrother.pickandtest.stock.domain.service.ports.output.SymbolFetcher;
import com.gyeryongbrother.pickandtest.stock.infrastructure.exception.StockInfrastructureException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SymbolFetcherImpl implements SymbolFetcher {

    @Override
    public List<String> fetchSymbols(StockExchange stockExchange) {
        ClassPathResource resource = new ClassPathResource(stockExchange.name().toLowerCase() + ".txt");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new StockInfrastructureException(RESOURCE_READ_FAILED);
        }
    }
}
