package com.ocado.isf;

import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;
import com.ocado.isf.mapper.ModelMapper;
import com.ocado.isf.model.Picker;
import com.ocado.isf.scheduler.Scheduler;
import com.ocado.isf.scheduler.greedy.GreedyScheduler;
import com.ocado.isf.scheduler.task1.SimpleOrderComparator;
import com.ocado.isf.scheduler.task1.SimplePickerComparator;
import com.ocado.isf.scheduler.task2.PriceOrderComparator;

import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Store store = ModelMapper.mapJsonToStore(args[1]);
        List<Order> orders = ModelMapper.mapJsonToOrdersList(args[0]);
        Scheduler scheduler = new GreedyScheduler(orders, store, new SimpleOrderComparator(), new SimplePickerComparator());
        List<Picker> pickers = scheduler.assign();
        System.out.println("--- Task 1 --- Maximizing the number of the orders:");
        pickers.forEach(System.out::println);

        Scheduler secondScheduler = new GreedyScheduler(orders, store, new PriceOrderComparator(), new SimplePickerComparator());
        List<Picker> pickersByPrice = secondScheduler.assign();
        System.out.println("--- Task 2 --- Maximizing the value of the orders:");
        pickersByPrice.forEach(System.out::println);
    }
}
