package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.Post;

public interface SubscribingPostAddToInboxUsecase {

    void savingSubscribingInboxPost(Post post);
}
