package com.fromdot.kafkahandson.ugc.consumer;

import com.fromdot.kafkahandson.ugc.CustomObjectMapper;
import com.fromdot.kafkahandson.ugc.IssueCouponUsecase;
import com.fromdot.kafkahandson.ugc.adapter.common.Topic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fromdot.kafkahandson.ugc.adapter.couponIssueRequest.CouponIssueRequestMessage;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponIssuingWorker {

    private final CustomObjectMapper objectMapper = new CustomObjectMapper();
    private final IssueCouponUsecase issueCouponUsecase;

    @KafkaListener(
            topics = {Topic.COUPON_ISSUE_REQUEST},
            groupId = "coupon-issue-request",
            concurrency = "3"
    )
    public void listen(ConsumerRecord<String, String> message) throws JsonProcessingException {
        CouponIssueRequestMessage couponIssueRequestMessage = objectMapper.readValue(message.value(), CouponIssueRequestMessage.class);
        issueCouponUsecase.save(
                couponIssueRequestMessage.getCouponEventId(),
                couponIssueRequestMessage.getUserId()
        );
    }

}
