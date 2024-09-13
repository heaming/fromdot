package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.port.SubscribingPostPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubscribingPostRemoveFromInboxService implements SubscribingPostRemoveFromInboxUsecase {

    private final SubscribingPostPort subscribingPostPort;

    @Override
    public void deleteSubscribingInboxPost(Long postId) {
        subscribingPostPort.removePostFromFollowerInboxes(postId);
    }
}
