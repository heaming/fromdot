package com.fromdot.kafkahandson.ugc.consumer;

import com.fromdot.kafkahandson.ugc.CustomObjectMapper;
import com.fromdot.kafkahandson.ugc.SubscribingPostAddToInboxUsecase;
import com.fromdot.kafkahandson.ugc.SubscribingPostRemoveFromInboxUsecase;
import com.fromdot.kafkahandson.ugc.adapter.common.OperationType;
import com.fromdot.kafkahandson.ugc.adapter.common.Topic;
import com.fromdot.kafkahandson.ugc.adapter.inspectedpost.InspectedPostMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContentSubscribingWorker { // write 작업만 (CUD)

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final SubscribingPostAddToInboxUsecase subscribingPostAddToInboxUsecase;
    private final SubscribingPostRemoveFromInboxUsecase subscribingPostRemoveFromInboxUsecase;

    @KafkaListener(
            topics = { Topic.INSPECTED_POST },
            groupId = "subscribing-post-consumer-group",
            concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        InspectedPostMessage inspectedPostMessage = objectMapper.readValue(message.value(), InspectedPostMessage.class);

        if(inspectedPostMessage.getOperationType() == OperationType.CREATE) {
            this.handleCreate(inspectedPostMessage);
        } else if (inspectedPostMessage.getOperationType() == OperationType.UPDATE) {

        } else if (inspectedPostMessage.getOperationType() == OperationType.DELETE) {
            this.handleDelete(inspectedPostMessage);
        }
    }

    private void handleCreate(InspectedPostMessage inspectedPostMessage) {
        subscribingPostAddToInboxUsecase.savingSubscribingInboxPost(inspectedPostMessage.getPayload().getPost());;
    }

    private void handleDelete(InspectedPostMessage inspectedPostMessage) {
        subscribingPostRemoveFromInboxUsecase.deleteSubscribingInboxPost(inspectedPostMessage.getId());
    }
}
