package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import java.util.Optional;

public interface OAuthCredentialQueryRepository {

    Optional<OAuthCredential> findByOauthId(OAuthId oauthId);
}
