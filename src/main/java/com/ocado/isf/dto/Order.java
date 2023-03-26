package com.ocado.isf.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

/***
 * Represents order object mapped from appropriate json file.
 */
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Getter
    private String orderId;

    @Getter
    private BigDecimal orderValue;

    @Getter
    private Duration pickingTime;

    @Getter
    private LocalTime completeBy;

    @Override
    public String toString() {
        return orderId;
    }
}
