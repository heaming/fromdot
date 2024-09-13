package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;
import lombok.Data;

import java.util.List;

public interface SubscribingPostListUsecase {

    List<ResolvedPost> listSubscribingInboxPosts(Request request);

    @Data
    class Request {
        private final int pageNumber;
        private final long followerUserId;
    }
}
