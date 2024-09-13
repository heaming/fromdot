package com.fromdot.kafkahandson.ugc.port;

import com.fromdot.kafkahandson.ugc.inspectedpost.model.InspectedPost;

public interface InspectedPostMessageProducePort {

    void sendCreateMessage(InspectedPost post);
    void sendUpdateMessage(InspectedPost post);
    void sendDeleteMessage(Long id);

}
