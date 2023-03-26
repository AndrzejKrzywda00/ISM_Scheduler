package com.ocado.isf.scheduler.task2;

import com.ocado.isf.dto.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceOrderComparatorTest {
    private final Comparator<Order> comparator = new PriceOrderComparator();

    @Test
    void testCompareOrders_durationShouldDecide() {
        Order o1 = new Order("order-1", BigDecimal.valueOf(100), Duration.ofMinutes(30), LocalTime.of(8, 0));
        Order o2 = new Order("order-2", BigDecimal.valueOf(100), Duration.ofMinutes(20), LocalTime.of(7, 30));
        assertTrue(comparator.compare(o1, o2) < 0);
    }

    @Test
    void testCompareOrders_priceShouldDecide() {
        Order o1 = new Order("order-1", BigDecimal.valueOf(110), Duration.ofMinutes(30), LocalTime.of(8, 0));
        Order o2 = new Order("order-2", BigDecimal.valueOf(100), Duration.ofMinutes(30), LocalTime.of(7, 30));
        assertTrue(comparator.compare(o1, o2) > 0);
    }
}
