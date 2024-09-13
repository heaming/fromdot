package com.fromdot.kafkahandson.ugc.coupon;

import com.fromdot.kafkahandson.ugc.coupon.model.CouponEvent;
import com.fromdot.kafkahandson.ugc.port.CouponEventPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CouponEventAdapter implements CouponEventPort {

    private final CouponEventJpaRepository couponEventJpaRepository;

    @Override
    public CouponEvent findById(Long id) {

        CouponEventEntity couponEventEntity = couponEventJpaRepository.findById(id).orElse(null);
        if(couponEventEntity == null) return null;
        return this.toModel(couponEventEntity);
    }

    private CouponEvent toModel(CouponEventEntity couponEventEntity) {
        return new CouponEvent(
                couponEventEntity.getId(),
                couponEventEntity.getDisplayName(),
                couponEventEntity.getExpiresAt(),
                couponEventEntity.getIssueLimit()
        );
    }

}
