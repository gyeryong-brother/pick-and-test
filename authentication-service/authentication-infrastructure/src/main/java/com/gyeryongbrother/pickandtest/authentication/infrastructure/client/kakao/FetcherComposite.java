package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Fetcher;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Request;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestContext;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.RequestProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.Url;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common.UrlProvider;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.common.Supportable;
import java.util.Map;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class FetcherComposite {

    private final Map<RequestType, UrlProvider> urlProvidersByType;
    private final Map<RequestType, RequestProvider> requestProvidersByType;
    private final Fetcher fetcher;

    public FetcherComposite(
            Set<UrlProvider> urlProviders,
            Set<RequestProvider> requestProviders,
            Fetcher fetcher
    ) {
        urlProvidersByType = urlProviders.stream()
                .collect(toMap(Supportable::support, identity()));
        requestProvidersByType = requestProviders.stream()
                .collect(toMap(Supportable::support, identity()));
        this.fetcher = fetcher;
    }

    public <T> T fetch(RequestType requestType, RequestContext requestContext, Class<T> clazz) {
        Url url = urlProvidersByType.get(requestType).provide();
        Request request = requestProvidersByType.get(requestType).provide(requestContext);
        return fetcher.fetch(
                requestType.httpMethod(),
                url,
                request,
                clazz
        );
    }
}
