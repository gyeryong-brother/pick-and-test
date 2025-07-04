package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.LoginType;
import java.util.List;

public interface Tokenizable {

    LoginType type();

    String subject();

    List<String> authorities();
}
