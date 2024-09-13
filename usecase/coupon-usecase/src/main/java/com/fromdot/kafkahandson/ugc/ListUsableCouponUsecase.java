package com.fromdot.kafkahandson.ugc;

import com.fromdot.kafkahandson.ugc.coupon.model.ResolvedCoupon;

import java.util.List;

public interface ListUsableCouponUsecase {

    List<ResolvedCoupon> listByUserId(Long userId);
}
