package com.fastcampus.kafkahandson.ugc;

import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fastcampus.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface SubscribingPostAddToInboxUsecase {

    void savingSubscribingInboxPost(Post post);
}
