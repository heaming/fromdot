package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.port.OriginalPostMessageProducePort;
import com.fromdot.kafkahandson.ugc.port.PostPort;
import com.fromdot.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostDeleteService implements PostDeleteUsecase {

    private final PostPort postPort;
    private final OriginalPostMessageProducePort originalPostMessageProducePort;

    @Transactional
    @Override
    public Post delete(Request request) {
        Post post = postPort.findById(request.getPostId());

        if(post == null) return null;

        post.delete();

        Post deletedPost = postPort.save(post);
        originalPostMessageProducePort.sendDeleteMessage(deletedPost.getId());
        return deletedPost;
    }
}
