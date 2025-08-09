package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;

public interface UsernamePasswordCredentialRepository {

    UsernamePasswordCredential save(UsernamePasswordCredential usernamePasswordCredential);
}
