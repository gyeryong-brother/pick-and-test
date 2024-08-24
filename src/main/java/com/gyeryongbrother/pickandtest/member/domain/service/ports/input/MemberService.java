package com.gyeryongbrother.pickandtest.member.domain.service.ports.input;

import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberCommand;
import com.gyeryongbrother.pickandtest.member.domain.service.dto.RegisterMemberResponse;

public interface MemberService {

    RegisterMemberResponse register(RegisterMemberCommand registerMemberCommand);
}
