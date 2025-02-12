package com.gyeryongbrother.pickandtest.member.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.member.domain.core.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String userId;

    private String password;

    private String refreshToken;

    private UserRole userRole;
}
