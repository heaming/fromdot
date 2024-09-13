package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;

import java.util.List;

public interface PostSearchUsecase {

    List<ResolvedPost> getSearchResultByKeyword(String keyword, int pageNumber);
}
