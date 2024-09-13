package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.coupon.model.ResolvedCoupon;
import com.fromdot.kafkahandson.ugc.port.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ListUsableCouponService implements ListUsableCouponUsecase {

    private final CouponPort couponPort;

    @Override
    public List<ResolvedCoupon> listByUserId(Long userId) {
        List<ResolvedCoupon> resolvedCoupons = couponPort.listByUserId(userId);
        return resolvedCoupons.stream().filter(ResolvedCoupon::canBeUsed).toList();
    }
}
