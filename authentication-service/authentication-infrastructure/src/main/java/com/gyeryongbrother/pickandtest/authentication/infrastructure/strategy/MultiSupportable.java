package com.gyeryongbrother.pickandtest.authentication.infrastructure.strategy;

import java.util.Set;

public interface MultiSupportable<T> {

    Set<T> support();
}
