package com.gyeryongbrother.pickandtest.member.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.member.domain.core.RefreshToken;
import java.util.List;

public interface RefreshTokenQueryRepository {

    List<RefreshToken> findByUsername(String username);
}
