package com.ocado.isf.scheduler.task2;

import com.ocado.isf.dto.Order;

import java.util.Comparator;

public class PriceOrderComparator implements Comparator<Order> {
    @Override
    public int compare(Order o1, Order o2) {
        double subValue1 = calculateValueToTimeMetric(o1);
        double subValue2 = calculateValueToTimeMetric(o2);
        return subValue1 > subValue2 ? 1 : subValue1 == subValue2 ? earlierOrder(o1, o2) : -1;
    }

    private double calculateValueToTimeMetric(Order order) {
        return order.getOrderValue().doubleValue() / order.getPickingTime().toMinutes();
    }

    private int earlierOrder(Order o1, Order o2) {
        return o1.getCompleteBy().compareTo(o2.getCompleteBy());
    }
}
