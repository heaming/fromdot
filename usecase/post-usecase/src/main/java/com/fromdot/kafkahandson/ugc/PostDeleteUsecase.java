package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.Post;
import lombok.Data;

public interface PostDeleteUsecase {

    Post delete(Request request);

    @Data
    class Request {
        private final Long postId;
    }
}
