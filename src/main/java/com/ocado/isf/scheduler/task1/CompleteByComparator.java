package com.ocado.isf.scheduler.task1;

import com.ocado.isf.dto.Order;

import java.util.Comparator;

public class CompleteByComparator implements Comparator<Order> {
    /***
     * Compare orders by complete by parameter, ascending
     */
    @Override
    public int compare(Order o1, Order o2) {
        return o1.getCompleteBy().compareTo(o2.getCompleteBy());
    }
}
