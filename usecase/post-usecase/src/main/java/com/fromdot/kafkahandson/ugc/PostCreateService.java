package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.port.OriginalPostMessageProducePort;
import com.fromdot.kafkahandson.ugc.port.PostPort;
import com.fromdot.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostCreateService implements PostCreateUsecase {

    private final PostPort postPort;
    private final OriginalPostMessageProducePort originalPostMessageProducePort;


    @Transactional
    @Override
    public Post create(Request request) {
        Post post = postPort.save(
            Post.generate(
                request.getUserId(),
                request.getTitle(),
                request.getContent(),
                request.getCategoryId()
            )
        );

        originalPostMessageProducePort.sendCreateMessage(post);
        return post;
    }
}
