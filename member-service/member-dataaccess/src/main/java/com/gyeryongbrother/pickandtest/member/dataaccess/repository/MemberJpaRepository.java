package com.gyeryongbrother.pickandtest.member.dataaccess.repository;

import com.gyeryongbrother.pickandtest.member.dataaccess.entity.MemberEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {

    List<MemberEntity> findByUsername(String username);
}
