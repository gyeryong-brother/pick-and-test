package com.gyeryongbrother.pickandtest.common.authorizationresolver;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MemberAuthorityArgumentResolver implements HandlerMethodArgumentResolver {

    private static final String MEMBER_ID_HEADER_NAME = "X-Member-Id";
    private static final String MEMBER_ROLES_HEADER_NAME = "X-Member-Roles";

    private final AccessValidatorComposite accessValidatorComposite;

    public MemberAuthorityArgumentResolver() {
        accessValidatorComposite = new AccessValidatorComposite();
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz = parameter.getParameterType();
        return MemberAuthority.class.isAssignableFrom(clazz);
    }

    @Override
    public Object resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        MemberAuthority memberAuthority = memberAuthority(request);
        accessValidatorComposite.validateAccess(memberAuthority, parameter);
        return memberAuthority;
    }

    private MemberAuthority memberAuthority(HttpServletRequest request) {
        Long memberId = memberId(request.getHeader(MEMBER_ID_HEADER_NAME));
        List<MemberRole> memberRoles = Arrays.stream(request.getHeader(MEMBER_ROLES_HEADER_NAME).split(","))
                .map(String::trim)
                .map(MemberRole::valueOf)
                .toList();
        return new MemberAuthority(memberId, memberRoles);
    }

    private Long memberId(String memberId) {
        if ("anonymous".equals(memberId)) {
            return null;
        }
        return Long.parseLong(memberId);
    }
}
