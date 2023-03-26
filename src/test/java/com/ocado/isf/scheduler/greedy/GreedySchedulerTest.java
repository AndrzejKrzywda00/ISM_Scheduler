package com.ocado.isf.scheduler.greedy;

import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;
import com.ocado.isf.mapper.ModelMapper;
import com.ocado.isf.model.Picker;
import com.ocado.isf.scheduler.Scheduler;
import com.ocado.isf.scheduler.task1.SimpleOrderComparator;
import com.ocado.isf.scheduler.task1.SimplePickerComparator;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreedySchedulerTest {
    private final String absolutePath = "src/test/resources/self-test-data/";
    private final Comparator<Order> orderComparator = new SimpleOrderComparator();
    private final Comparator<Picker> pickerComparator = new SimplePickerComparator();

    @Test
    void testAssignForTwoOrdersAndPicker_oneOrderShouldBeAssigned() throws IOException {
        Store store = ModelMapper.mapJsonToStore(absolutePath + "complete-by/store.json");
        List<Order> orders = ModelMapper.mapJsonToOrdersList(absolutePath + "complete-by/orders.json");
        Scheduler scheduler = new GreedyScheduler(orders, store, orderComparator, pickerComparator);
        List<Picker> pickers = scheduler.assign();
        int expectedPickersSize = 1;
        int expectedAssignedOrders = 1;
        assertEquals(expectedPickersSize, pickers.size());
        assertEquals(expectedAssignedOrders, pickers.get(0).getOrders().size());
    }

    @Test
    void testAssignForManyOrdersAndSinglePicker_someOrdersShouldBeAssigned() throws IOException {
        Store store = ModelMapper.mapJsonToStore(absolutePath + "optimize-order-count/store.json");
        List<Order> orders = ModelMapper.mapJsonToOrdersList(absolutePath + "optimize-order-count/orders.json");
        Scheduler scheduler = new GreedyScheduler(orders, store, orderComparator, pickerComparator);
        List<Picker> pickers = scheduler.assign();
        int expectedPickersSize = 1;
        String[] expectedOrders = {"order-2", "order-3"};
        assertEquals(expectedPickersSize, pickers.size());
        assertEquals(expectedOrders.length, pickers.get(0).getOrders().size());
        assertEquals(expectedOrders[0], pickers.get(0).getOrders().get(0).getOrderId());
        assertEquals(expectedOrders[1], pickers.get(0).getOrders().get(1).getOrderId());
    }

    @Test
    void testAssignForOrderAndPicker_nothingShouldBeAssigned() throws IOException {
        Store store = ModelMapper.mapJsonToStore(absolutePath + "impossible-assignment/store.json");
        List<Order> orders = ModelMapper.mapJsonToOrdersList(absolutePath + "impossible-assignment/orders.json");
        Scheduler scheduler = new GreedyScheduler(orders, store, orderComparator, pickerComparator);
        List<Picker> pickers = scheduler.assign();
        assertEquals(0, pickers.get(0).getOrders().size());
    }
}
