package com.ocado.isf.scheduler.advanced;

import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;
import com.ocado.isf.model.Picker;
import com.ocado.isf.scheduler.Scheduler;
import com.ocado.isf.scheduler.task1.CompleteByComparator;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class AdvancedScheduler implements Scheduler {
    private final List<Order> orders;
    private final Store store;

    public AdvancedScheduler(List<Order> orders, Store store) {
        this.orders = orders;
        this.store = store;
    }

    @Override
    public List<Picker> assign() {
        List<Picker> pickers = createEmptyPickers();
        int[][] assignments = new int[orders.size()][pickers.size()];
        LocalTime[] times = new LocalTime[pickers.size()];

        orders.sort(new CompleteByComparator());
        Order startOrder = orders.get(0);
        Arrays.fill(times, store.getPickingStartTime().plus(startOrder.getPickingTime()));
        for (int i=0; i<pickers.size(); i++) {
            assignments[0][i] = 1;
        }

        for (int j=1; j< orders.size(); j++) {
            Order order = orders.get(j);
            for (int i=0; i<pickers.size(); i++) {
                int value1 = 0;
                if (!times[i].plus(order.getPickingTime()).isAfter(order.getCompleteBy())
                && !times[i].plus(order.getPickingTime()).isAfter(store.getPickingEndTime())) {
                    value1 = i >= 1 ? assignments[j-1][i-1] + 1 : assignments[j-1][i] + 1;
                }
                int value2 = assignments[j-1][i];
                if (value1 > value2) {
                    times[i] = times[i].plus(order.getPickingTime());
                }
                assignments[j][i] = Math.max(value1, value2);
            }
        }

        for (int p=0; p<orders.size(); p++) {
            for (int i=0; i<pickers.size(); i++) {
                System.out.print(assignments[p][i] + " ");
            }
            System.out.println();
        }

        int maxCapacity = 0;
        int jt = orders.size()-1;
        for (Order order : orders) {
            for (int i = 0; i < pickers.size(); i++) {
                if (assignments[jt][i] > maxCapacity) {
                    maxCapacity = assignments[jt][i];
                }
            }
            for (int i = 0; i < pickers.size(); i++) {
                if (assignments[jt][i] == maxCapacity) {
                    Picker picker = pickers.get(i);
                    if (!picker.currentStartingTime().plus(order.getPickingTime()).isAfter(order.getCompleteBy())) {
                        picker.addOrder(order);
                        break;
                    }
                }
            }
            maxCapacity = 0;
        }

        return pickers;
    }

    private List<Picker> createEmptyPickers() {
        return store.getPickers()
                .stream()
                .map(id -> new Picker(id, store.getPickingStartTime()))
                .collect(Collectors.toList());
    }
}
