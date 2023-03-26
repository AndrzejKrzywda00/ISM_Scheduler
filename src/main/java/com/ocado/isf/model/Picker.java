package com.ocado.isf.model;

import com.ocado.isf.dto.Order;
import lombok.Getter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/***
 * Represents picker (employee) with assigned orders.
 */
public class Picker {
    @Getter
    private final String pickerId;
    @Getter
    private final List<Order> orders;
    private final List<LocalTime> pickingStartTimes;

    public Picker(String id, LocalTime startingTime) {
        pickerId = id;
        orders = new ArrayList<>();
        pickingStartTimes = new ArrayList<>();
        pickingStartTimes.add(startingTime);
    }

    public void addOrder(Order order) {
        orders.add(order);
        LocalTime currentTime = currentStartingTime();
        pickingStartTimes.add(currentTime.plus(order.getPickingTime()));
    }

    public LocalTime currentStartingTime() {
        return pickingStartTimes.get(pickingStartTimes.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<orders.size(); i++) {
            sb.append(pickerId).append(" ")
                    .append(orders.get(i).getOrderId()).append(" ")
                    .append(pickingStartTimes.get(i)).append("\n");
        }
        return sb.toString();
    }
}
