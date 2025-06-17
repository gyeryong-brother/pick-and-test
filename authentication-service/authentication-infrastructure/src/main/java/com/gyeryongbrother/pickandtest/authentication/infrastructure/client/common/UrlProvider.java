package com.gyeryongbrother.pickandtest.authentication.infrastructure.client.common;

import com.gyeryongbrother.pickandtest.authentication.infrastructure.client.kakao.common.RequestType;
import com.gyeryongbrother.pickandtest.authentication.infrastructure.common.Supportable;

public interface UrlProvider extends Supportable<RequestType> {

    Url provide();
}
