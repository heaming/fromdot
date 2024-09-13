package com.fromdot.kafkahandson.ugc.post;

import com.fromdot.kafkahandson.ugc.port.PostPort;
import com.fromdot.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fromdot.kafkahandson.ugc.post.PostEntityConverter.toEntity;
import static com.fromdot.kafkahandson.ugc.post.PostEntityConverter.toModel;

@RequiredArgsConstructor
@Component
public class PostAdapter implements PostPort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public Post save(Post post) {
        PostEntity postEntity = postJpaRepository.save(toEntity(post));

        return toModel(postEntity);
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = postJpaRepository.findById(id).orElse(null);

        if(postEntity == null) {
            return null;
        }

        return toModel(postEntity);
    }

    @Override
    public List<Post> listByIds(List<Long> ids) {
        List<PostEntity> postEntities = postJpaRepository.findAllById(ids);
        return postEntities.stream().map(PostEntityConverter::toModel).toList();
    }
}
