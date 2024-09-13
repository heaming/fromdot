package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.inspectedpost.AutoInspectionResult;
import com.fromdot.kafkahandson.ugc.post.model.Post;

public interface PostAutoInspectPort {

    AutoInspectionResult inspect(Post post, String categoryName);
}
