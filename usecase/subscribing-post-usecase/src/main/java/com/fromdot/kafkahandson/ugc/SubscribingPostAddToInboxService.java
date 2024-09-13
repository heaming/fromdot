package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.port.MetadataPort;
import com.fromdot.kafkahandson.ugc.port.SubscribingPostPort;
import com.fromdot.kafkahandson.ugc.post.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubscribingPostAddToInboxService implements SubscribingPostAddToInboxUsecase {

    private final SubscribingPostPort subscribingPostPort;
    private final MetadataPort metadataPort;

    @Override
    public void savingSubscribingInboxPost(Post post) {
        List<Long> followerUserIds = metadataPort.listFollowerIdsByUserId(post.getUserId());
        subscribingPostPort.addPostToFollowerInboxed(post, followerUserIds);
    }
}
