package com.fromdot.kafkahandson.ugc.coupon.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Coupon {

    private Long id;
    private Long userId;
    private Long couponEventId;
    private LocalDateTime issuedAt;
    private LocalDateTime usedAt;

    public Coupon use() {
        this.usedAt = LocalDateTime.now();
        return this;
    }

    public static Coupon generate(
            Long couponEventId,
            Long userId
    ) {
        return new Coupon(null, userId, couponEventId, LocalDateTime.now(), null);
    }

}
