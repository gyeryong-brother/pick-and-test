package com.gyeryongbrother.pickandtest.member.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;

public interface MemberQueryRepository {

    Member findByUsername(String username);
}
