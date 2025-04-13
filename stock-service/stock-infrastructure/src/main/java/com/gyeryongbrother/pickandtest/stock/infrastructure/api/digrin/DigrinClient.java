package com.gyeryongbrother.pickandtest.stock.infrastructure.api.digrin;

import com.gyeryongbrother.pickandtest.stock.infrastructure.api.digrin.common.DigrinStockExchange;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DigrinClient {

    public List<String> fetchSymbols(DigrinStockExchange digrinStockExchange) {
        return fetchSymbols(digrinStockExchange.name().toLowerCase());
    }

    private List<String> fetchSymbols(String exchange) {
        ClassPathResource resource = new ClassPathResource(exchange + ".txt");
        try (InputStream inputStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
