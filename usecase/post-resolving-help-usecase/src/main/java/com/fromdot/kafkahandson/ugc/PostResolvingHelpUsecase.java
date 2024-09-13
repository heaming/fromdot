package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.Post;
import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface PostResolvingHelpUsecase {

    ResolvedPost resolvedPostById(Long postId);
    // 상세 페이지
    List<ResolvedPost> resolvedPostsByIds(List<Long> postIds); // 목록(검색, 구독)

    void resolvePostAndSave(Post post);

    void deleteResolvedPost(Long postId);

}
