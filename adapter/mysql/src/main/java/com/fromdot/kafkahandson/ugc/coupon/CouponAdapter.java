package com.fromdot.kafkahandson.ugc.coupon;

import com.fromdot.kafkahandson.ugc.coupon.model.Coupon;
import com.fromdot.kafkahandson.ugc.coupon.model.ResolvedCoupon;
import com.fromdot.kafkahandson.ugc.port.CouponPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.fromdot.kafkahandson.ugc.coupon.CouponEntityConverter.*;

@RequiredArgsConstructor
@Component
public class CouponAdapter implements CouponPort {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public Coupon save(Coupon coupon) {
        CouponEntity couponEntity = couponJpaRepository.save(toCouponEntity(coupon));
        return toCouponModel(couponEntity);
    }

    @Override
    public List<ResolvedCoupon> listByUserId(Long userId) {
        return couponJpaRepository.findAllByUserId(userId)
                .stream()
                .map(CouponEntityConverter::toResolvedCouponModel)
                .toList();
    }

}
