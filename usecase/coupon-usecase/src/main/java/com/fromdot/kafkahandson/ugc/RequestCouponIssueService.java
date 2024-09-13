package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.port.CouponIssueRequestPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RequestCouponIssueService implements RequestCouponIssueUsecase {

    private final CouponIssueRequestPort couponIssueRequestPort;

    @Override
    public void queue(Long couponEventId, Long userId) {
        couponIssueRequestPort.sendMessage(couponEventId, userId);
    }
}
