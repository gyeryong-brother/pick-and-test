package com.gyeryongbrother.pickandtest.authentication.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;

import com.gyeryongbrother.pickandtest.authentication.domain.core.entity.RefreshToken;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "refresh_token")
public class RefreshTokenEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private Long memberId;

    private String token;

    public RefreshToken toDomain() {
        return new RefreshToken(id, memberId, token);
    }
}
