package com.gyeryongbrother.pickandtest.noticeboard.dataaccess.adapter;

import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.entity.PostEntity;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.mapper.PostDataAccessMapper;
import com.gyeryongbrother.pickandtest.noticeboard.dataaccess.repository.PostJpaRepository;
import com.gyeryongbrother.pickandtest.noticeboard.domain.core.entity.Post;
import com.gyeryongbrother.pickandtest.noticeboard.domain.service.ports.output.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final PostJpaRepository postJpaRepository;
    private final PostDataAccessMapper postDataAccessMapper;

    @Override
    public Post save(Post post) {
        PostEntity postEntity=postDataAccessMapper.postToPostEntity(post);
        PostEntity saved=postJpaRepository.save(postEntity);
        return postDataAccessMapper.postEntityToPost(saved);
    }

    @Override
    public void deleteById(Long id) {
        postJpaRepository.deleteById(id);
    }
}
