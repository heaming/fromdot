package com.fromdot.kafkahandson.ugc;

public interface SubscribingPostRemoveFromInboxUsecase {

    void deleteSubscribingInboxPost(Long postId);
}
