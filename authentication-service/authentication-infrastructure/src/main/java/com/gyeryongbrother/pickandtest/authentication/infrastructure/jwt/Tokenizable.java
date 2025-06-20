package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import java.util.List;

public interface Tokenizable {

    String subject();

    List<String> authorities();
}
