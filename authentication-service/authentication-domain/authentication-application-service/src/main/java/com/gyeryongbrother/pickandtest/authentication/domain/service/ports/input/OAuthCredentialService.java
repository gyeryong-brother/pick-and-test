package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OAuthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.OAuthMember;

public interface OAuthCredentialService {

    OAuthCredential getOrRegisterOAuthCredential(OAuthMember oAuthMember);
}
