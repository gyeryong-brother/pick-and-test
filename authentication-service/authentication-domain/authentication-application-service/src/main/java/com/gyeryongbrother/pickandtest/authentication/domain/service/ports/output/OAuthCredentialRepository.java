package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;

public interface OAuthCredentialRepository {

    OAuthCredential save(OAuthCredential oauthCredential);
}
