package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import java.util.Optional;

public interface OauthCredentialQueryRepository {

    Optional<OauthCredential> findByOauthId(String oauthId);
}
