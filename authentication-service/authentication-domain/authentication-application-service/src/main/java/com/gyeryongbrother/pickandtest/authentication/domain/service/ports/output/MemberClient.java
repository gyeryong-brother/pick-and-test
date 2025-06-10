package com.gyeryongbrother.pickandtest.authentication.domain.service.ports.output;

import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.Member;

public interface MemberClient {

    Member registerMember(Member member);
}
