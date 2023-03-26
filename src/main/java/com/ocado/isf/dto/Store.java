package com.ocado.isf.dto;

import lombok.Getter;
import java.time.LocalTime;
import java.util.List;

public class Store {
    @Getter
    private List<String> pickers;

    @Getter
    private LocalTime pickingStartTime;

    @Getter
    private LocalTime pickingEndTime;
}
