package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<CommentEntity,Long> {
}
