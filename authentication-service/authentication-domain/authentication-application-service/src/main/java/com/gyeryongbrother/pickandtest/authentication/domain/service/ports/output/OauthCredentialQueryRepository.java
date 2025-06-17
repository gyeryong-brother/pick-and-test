package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OauthId;
import java.util.Optional;

public interface OauthCredentialQueryRepository {

    Optional<OauthCredential> findByOauthId(OauthId oauthId);
}
