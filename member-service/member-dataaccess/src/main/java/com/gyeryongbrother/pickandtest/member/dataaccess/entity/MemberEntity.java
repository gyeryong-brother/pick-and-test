package com.gyeryongbrother.pickandtest.member.dataaccess.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import com.gyeryongbrother.pickandtest.member.domain.core.Member;
import com.gyeryongbrother.pickandtest.member.domain.core.MemberRole;
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
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private MemberRole memberRole;
    private String nickname;
    private String profileImageUrl;

    public Member toDomain() {
        return new Member(id, memberRole, nickname, profileImageUrl);
    }
}
