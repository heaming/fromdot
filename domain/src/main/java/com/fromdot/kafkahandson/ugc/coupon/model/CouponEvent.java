package com.fromdot.kafkahandson.ugc.coupon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CouponEvent {

    private Long id;
    private String displayName;
    private LocalDateTime expiresAt;
    private Long issueLimit;

    @JsonIgnore
    public boolean isExpired() {
        return expiresAt.isBefore(LocalDateTime.now());
    }

}
