package com.gyeryongbrother.pickandtest.member.application.config;

import com.gyeryongbrother.pickandtest.common.authorizationresolver.MemberAuthorityArgumentResolver;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberAuthorityArgumentResolver());
    }
}
