package com.fromdot.kafkahandson.ugc.adapter.originalpost;

import com.fromdot.kafkahandson.ugc.post.model.Post;

public class OriginalPostMessageConverter {

    public static Post toModel(OriginalPostMessage originalPostMessage) {
        return new Post(
                originalPostMessage.getId(),
                originalPostMessage.getPayload().getTitle(),
                originalPostMessage.getPayload().getContent(),
                originalPostMessage.getPayload().getUserId(),
                originalPostMessage.getPayload().getCategoryId(),
                originalPostMessage.getPayload().getCreatedAt(),
                originalPostMessage.getPayload().getUpdatedAt(),
                originalPostMessage.getPayload().getDeletedAt()
        );
    }
}
