package com.ocado.isf.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ocado.isf.dto.Order;
import com.ocado.isf.dto.Store;

import java.io.File;
import java.io.IOException;
import java.util.List;

/***
 * This class is responsible for mapping json files to model objets.
 */
public class ModelMapper {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static Store mapJsonToStore(String jsonPath) throws IOException {
        return mapper.readValue(new File(jsonPath), Store.class);
    }

    public static List<Order> mapJsonToOrdersList(String jsonPath) throws IOException {
        return mapper.readValue(new File(jsonPath), new TypeReference<>(){});
    }
}
