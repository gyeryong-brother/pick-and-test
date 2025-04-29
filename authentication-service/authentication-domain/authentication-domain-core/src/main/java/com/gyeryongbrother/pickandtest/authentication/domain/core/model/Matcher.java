package com.gyeryongbrother.pickandtest.authentication.domain.core.model;

@FunctionalInterface
public interface Matcher {

    boolean matches(String secret);
}
