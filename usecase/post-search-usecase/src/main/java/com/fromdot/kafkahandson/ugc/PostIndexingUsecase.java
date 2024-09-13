package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.inspectedpost.model.InspectedPost;

public interface PostIndexingUsecase {

    void save(InspectedPost post);

    void delete(Long postId);
}
