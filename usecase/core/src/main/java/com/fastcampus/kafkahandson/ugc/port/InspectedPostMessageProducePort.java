package com.fastcampus.kafkahandson.ugc.port;

import com.fastcampus.kafkahandson.ugc.inspectedpost.InspectedPost;

public interface InspectedPostMessageProducePort {

    void sendCreateMessage(InspectedPost post);
    void sendUpdateMessage(InspectedPost post);
    void sendDeleteMessage(Long id);

}
