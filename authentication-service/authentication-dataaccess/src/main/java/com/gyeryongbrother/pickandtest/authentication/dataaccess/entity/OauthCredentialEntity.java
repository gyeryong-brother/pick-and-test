package com.gyeryongbrother.pickandtest.authentication.dataaccess.entity;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.OauthCredential;
import com.gyeryongbrother.pickandtest.authentication.domain.core.valueobject.AuthenticationMethod;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthCredentialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private String oauthId;

    @Enumerated(EnumType.STRING)
    private AuthenticationMethod authenticationMethod;

    public OauthCredential toDomain() {
        return new OauthCredential(
                id,
                memberId,
                oauthId,
                authenticationMethod
        );
    }
}
