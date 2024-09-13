package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fromdot.kafkahandson.ugc.port.PostSearchPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostIndexingService implements PostIndexingUsecase {

    private final PostSearchPort postSearchPort;

    @Override
    public void save(InspectedPost post) {
        postSearchPort.indexPost(post);
    }

    @Override
    public void delete(Long postId) {
        postSearchPort.deletePost(postId);
    }
}
