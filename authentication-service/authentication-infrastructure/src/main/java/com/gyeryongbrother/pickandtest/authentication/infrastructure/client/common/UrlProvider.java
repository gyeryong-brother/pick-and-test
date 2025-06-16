package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;

public interface UrlProvider extends Supportable<RequestType> {

    Url provide();
}
