package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.inspectedpost.model.InspectedPost;
import com.fromdot.kafkahandson.ugc.post.model.Post;

public interface PostInspectUsecase {

    InspectedPost inspectAndGetIfValid(Post post);
}
