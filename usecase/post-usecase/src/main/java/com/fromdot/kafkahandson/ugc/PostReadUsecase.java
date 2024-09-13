package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.post.model.ResolvedPost;

public interface PostReadUsecase {

    ResolvedPost getById(Long id);
}
