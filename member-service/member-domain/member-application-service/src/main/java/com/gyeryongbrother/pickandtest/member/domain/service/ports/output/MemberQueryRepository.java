package com.gyeryongbrother.pickandtest.member.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import java.util.Optional;

public interface MemberQueryRepository {

    Member getByUsername(String username);

    Optional<Member> findByUsername(String username);
}
