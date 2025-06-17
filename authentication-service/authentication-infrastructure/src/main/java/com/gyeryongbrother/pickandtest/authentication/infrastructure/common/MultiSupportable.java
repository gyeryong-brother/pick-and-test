package com.gyeryongbrother.pickandtest.authentication.infrastructure.common;

import java.util.Set;

public interface MultiSupportable<T> {

    Set<T> support();
}
