package com.ocado.isf.scheduler.task1;

import com.ocado.isf.dto.Order;

import java.util.Comparator;

public class SimpleOrderComparator implements Comparator<Order> {
    /***
     * Compares orders by their picking time, increasing.
     * Then compares them by duration, increasing.
     */
    @Override
    public int compare(Order o1, Order o2) {
        int comparingCompleteBy = o1.getCompleteBy().compareTo(o2.getCompleteBy());
        if (comparingCompleteBy == 0) return o1.getPickingTime().compareTo(o2.getPickingTime());
        return comparingCompleteBy;
    }
}
