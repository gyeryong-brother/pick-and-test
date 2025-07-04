package com.gyeryongbrother.pickandtest.authentication.infrastructure.jwt;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.security.LoginType;
import java.util.List;

public interface Tokenizable {

    LoginType type();

    String subject();

    List<String> authorities();
}
