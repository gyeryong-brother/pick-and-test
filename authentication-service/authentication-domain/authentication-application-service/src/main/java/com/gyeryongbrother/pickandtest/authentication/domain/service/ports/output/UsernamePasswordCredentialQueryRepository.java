package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.UsernamePasswordCredential;
import java.util.Optional;

public interface UsernamePasswordCredentialQueryRepository {

    Optional<UsernamePasswordCredential> findByUsername(String username);
}
