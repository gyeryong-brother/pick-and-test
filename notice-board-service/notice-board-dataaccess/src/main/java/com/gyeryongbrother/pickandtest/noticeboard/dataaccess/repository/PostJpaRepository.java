package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaRepository extends JpaRepository<PostEntity,Long> {
}
