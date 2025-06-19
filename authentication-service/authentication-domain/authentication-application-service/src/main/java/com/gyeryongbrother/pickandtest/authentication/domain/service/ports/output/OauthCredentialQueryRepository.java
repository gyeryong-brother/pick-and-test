package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthId;
import java.util.Optional;

public interface OauthCredentialQueryRepository {

    Optional<OauthCredential> findByOauthId(OAuthId oauthId);
}
