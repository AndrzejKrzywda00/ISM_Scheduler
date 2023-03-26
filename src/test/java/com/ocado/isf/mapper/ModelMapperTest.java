package com.ocado.isf.mapper;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelMapperTest {
    private final String absolutePath = "src/test/resources/self-test-data/";

    @Test
    void testMapJsonToStore_shouldHaveCorrectValues() throws IOException {
        String path = absolutePath + "complete-by/store.json";
        int pickers = 1;
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(10, 0);
        Store store = ModelMapper.mapJsonToStore(path);

        assertEquals(pickers, store.getPickers().size());
        assertEquals(startTime, store.getPickingStartTime());
        assertEquals(endTime, store.getPickingEndTime());
    }

    @Test
    void testMapJsonToStore_shouldThrowMME_whenIncorrectFileStructure() {
        String path = absolutePath + "isf-end-time/orders.json";
        assertThrows(MismatchedInputException.class, () -> ModelMapper.mapJsonToStore(path));
    }

    @Test
    void testMapJsonToOrders_shouldHaveCorrectSize() throws IOException {
        String path = absolutePath + "optimize-order-count/orders.json";
        int ordersSize = 3;
        List<Order> orders = ModelMapper.mapJsonToOrdersList(path);

        assertEquals(ordersSize, orders.size());
    }

    @Test
    void testMapJsonToOrders_shouldHaveCorrectValues() throws IOException {
        String path = absolutePath + "single-order-in-list/orders.json";
        int ordersSize = 1;
        String expectedId = "order-1";
        BigDecimal expectedValue = BigDecimal.valueOf(40.00);
        Duration expectedPickingTime = Duration.ofMinutes(60);
        LocalTime expectedCompleteBy = LocalTime.of(10, 0);
        List<Order> orders = ModelMapper.mapJsonToOrdersList(path);

        assertEquals(ordersSize, orders.size());
        Order order = orders.get(0);
        assertEquals(expectedId, order.getOrderId());
        assertEquals(expectedPickingTime, order.getPickingTime());
        assertEquals(expectedCompleteBy, order.getCompleteBy());
        assertEquals(0, expectedValue.compareTo(order.getOrderValue()));
    }

    @Test
    void testMapJsonToOrders_shouldThrowMME_whenIncorrectFileStructure() {
        String path = absolutePath + "complete-by/store.json";
        assertThrows(MismatchedInputException.class, () -> ModelMapper.mapJsonToOrdersList(path));
    }
}
