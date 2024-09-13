package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.post.model.Post;

import java.util.List;

public interface SubscribingPostPort {

    // 콘텐츠 발행 -> 구독하고 있는 모두의 구독함에 넣는다.
    void addPostToFollowerInboxed(Post post, List<Long> followerUserIds);

    void removePostFromFollowerInboxes(Long postId);

    // 특정 구독자의 목록 화면에서, 구독자가 구독하고 있는 유저가 생성한 콘텐츠 목록
    List<Long> listPostIdsByFollowerUserIdWithPagination(Long followerUserId, int pageNumber, int pageSize);
}
