package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import static lombok.AccessLevel.PRIVATE;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = PRIVATE)
public class Symbols {

    private final Set<String> values;

    public static Symbols from(List<String> values) {
        return new Symbols(new HashSet<>(values));
    }

    public void removeAll(List<String> symbols) {
        symbols.forEach(values::remove);
    }

    public Set<String> values() {
        return values;
    }
}
