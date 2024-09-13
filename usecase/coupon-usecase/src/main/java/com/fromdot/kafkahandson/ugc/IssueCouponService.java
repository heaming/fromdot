package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.coupon.model.Coupon;
import com.fromdot.kafkahandson.ugc.port.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IssueCouponService implements IssueCouponUsecase {

    private final CouponPort couponPort;

    @Override
    public Coupon save(Long couponEventId, Long userId) {
        Coupon coupon = Coupon.generate(couponEventId, userId);
        return couponPort.save(coupon);
    }
}
