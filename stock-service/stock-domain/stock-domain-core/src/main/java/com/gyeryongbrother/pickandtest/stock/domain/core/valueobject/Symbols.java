package com.gyeryongbrother.pickandtest.stock.domain.core.valueobject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Symbols {

    private final Set<String> values;

    public Symbols(List<String> values) {
        this.values = new HashSet<>(values);
    }

    public void removeAll(List<String> symbols) {
        symbols.forEach(values::remove);
    }

    public Set<String> values() {
        return values;
    }
}
