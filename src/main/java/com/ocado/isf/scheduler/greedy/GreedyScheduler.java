package com.ocado.isf.scheduler.greedy;

import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;
import com.ocado.isf.model.Picker;
import com.ocado.isf.scheduler.Scheduler;

import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.stream.Collectors;

/***
 * Schedules orders to a selected number of pickers,
 * maximizing the number of orders that will be completed.
 * Uses greedy approach.
 */
public class GreedyScheduler implements Scheduler {
    private final Queue<Order> orderQueue;
    private final Store store;
    private final Comparator<Picker> pickerComparator;

    public GreedyScheduler(List<Order> orders, Store store, Comparator<Order> orderComparator, Comparator<Picker> pickerComparator) {
        orderQueue = new PriorityQueue<>(orderComparator);
        orderQueue.addAll(orders);
        this.store = store;
        this.pickerComparator = pickerComparator;
    }

    @Override
    public List<Picker> assign() {
        Queue<Picker> pickers = createEmptyPickers(store.getPickers(), store.getPickingStartTime());
        while (!orderQueue.isEmpty()) {
            Order topOrder = orderQueue.poll();
            Queue<Picker> pqCopy = new PriorityQueue<>(pickers);
            Picker modifiedPicker = null;
            while (!pqCopy.isEmpty()) {
                Picker topPicker = pqCopy.poll();
                if (!topPicker.currentStartingTime().plus(topOrder.getPickingTime()).isAfter(topOrder.getCompleteBy())) {
                    topPicker.addOrder(topOrder);
                    modifiedPicker = topPicker;
                    break;
                }
            }
            if (modifiedPicker != null) {
                pickers.remove(modifiedPicker);
                pickers.add(modifiedPicker);
            }
        }
        return new ArrayList<>(pickers);
    }

    private Queue<Picker> createEmptyPickers(List<String> employeeIds, LocalTime startingTime) {
        return employeeIds
                .stream()
                .map(id -> new Picker(id, startingTime))
                .collect(Collectors.toCollection(
                        () -> new PriorityQueue<>(pickerComparator)
                ));
    }
}
