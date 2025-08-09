package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import java.util.List;
import java.util.Optional;

public interface RefreshTokenQueryRepository {

    List<RefreshToken> findByMemberId(Long memberId);

    Optional<RefreshToken> findByToken(String token);
}
