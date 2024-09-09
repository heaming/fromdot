package com.fastcampus.kafkahandson.ugc.adapter.inspectedpost;

import com.fastcampus.kafkahandson.ugc.CustomObjectMapper;
import com.fastcampus.kafkahandson.ugc.adapter.common.OperationType;
import com.fastcampus.kafkahandson.ugc.adapter.originpost.OriginalPostMessage;
import com.fastcampus.kafkahandson.ugc.inspectedpost.InspectedPost;
import com.fastcampus.kafkahandson.ugc.port.InspectedPostMessageProducePort;
import com.fastcampus.kafkahandson.ugc.port.OriginalPostMessageProducePort;
import com.fastcampus.kafkahandson.ugc.post.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.fastcampus.kafkahandson.ugc.adapter.common.Topic.INSPECTED_POST;
import static com.fastcampus.kafkahandson.ugc.adapter.common.Topic.ORIGINAL_POST;

@RequiredArgsConstructor
@Component
public class InspectedPostMessageProduceAdapter implements InspectedPostMessageProducePort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendCreateMessage(InspectedPost inspectedPost) {
        InspectedPostMessage message = new InspectedPostMessage(
                inspectedPost.getPost().getId(),
                new InspectedPostMessage.Payload(
                        inspectedPost.getPost(),
                        inspectedPost.getCategoryName(),
                        inspectedPost.getAutoGeneratedTags(),
                        inspectedPost.getInspectedAt()
                ),
                OperationType.CREATE
        );
        this.sendMessage(message);
    }

    @Override
    public void sendUpdateMessage(InspectedPost inspectedPost) {
        InspectedPostMessage message = new InspectedPostMessage(
                inspectedPost.getPost().getId(),
                new InspectedPostMessage.Payload(
                        inspectedPost.getPost(),
                        inspectedPost.getCategoryName(),
                        inspectedPost.getAutoGeneratedTags(),
                        inspectedPost.getInspectedAt()
                ),
                OperationType.UPDATE
        );
        this.sendMessage(message);
    }

    @Override
    public void sendDeleteMessage(Long postId) {
        InspectedPostMessage message = new InspectedPostMessage(
                postId,
                null,
                OperationType.DELETE
        );
        this.sendMessage(message);
    }

    private void sendMessage(InspectedPostMessage message) {
        try {
            kafkaTemplate.send(INSPECTED_POST, message.getId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
