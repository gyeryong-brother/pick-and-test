package com.gyeryongbrother.pickandtest.authentication.infrastructure.authentication.jwt;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.LoginType;
import java.util.List;

public interface Tokenizable {

    LoginType type();

    String subject();

    List<String> authorities();
}
