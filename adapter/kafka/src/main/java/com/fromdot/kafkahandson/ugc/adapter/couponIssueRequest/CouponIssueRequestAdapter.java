package com.fromdot.kafkahandson.ugc.adapter.couponIssueRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fromdot.kafkahandson.ugc.CustomObjectMapper;
import com.fromdot.kafkahandson.ugc.port.CouponIssueRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.fromdot.kafkahandson.ugc.adapter.common.Topic.COUPON_ISSUE_REQUEST;

@RequiredArgsConstructor
@Component
public class CouponIssueRequestAdapter implements CouponIssueRequestPort {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final CustomObjectMapper objectMapper = new CustomObjectMapper();

    @Override
    public void sendMessage(Long couponEventId, Long userId) {
        CouponIssueRequestMessage message = CouponIssueRequestMessage.from(couponEventId, userId);

        try {
            kafkaTemplate.send(COUPON_ISSUE_REQUEST, message.getUserId().toString(), objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
