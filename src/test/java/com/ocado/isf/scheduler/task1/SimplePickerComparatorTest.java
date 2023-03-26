package com.ocado.isf.scheduler.task1;

import com.ocado.isf.dto.Order;
import com.ocado.isf.model.Picker;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimplePickerComparatorTest {
    private final Comparator<Picker> comparator = new SimplePickerComparator();

    @Test
    void testComparePickers_shouldCompareByCurrentStartingTime() {
        Picker picker1 = new Picker("P1", LocalTime.of(9, 0));
        Picker picker2 = new Picker("P2", LocalTime.of(9, 0));
        Order o1 = new Order("order-1", BigDecimal.ZERO, Duration.ofMinutes(30), LocalTime.of(8, 0));
        Order o2 = new Order("order-2", BigDecimal.ZERO, Duration.ofMinutes(40), LocalTime.of(8, 0));
        picker1.addOrder(o1);
        picker2.addOrder(o2);

        assertTrue(comparator.compare(picker1, picker2) > 0);
    }
}
