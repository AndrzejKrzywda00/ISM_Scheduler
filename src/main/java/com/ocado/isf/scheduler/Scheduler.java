package com.ocado.isf.scheduler;

import com.ocado.isf.model.Picker;
import java.util.List;

public interface Scheduler {
    /***
     * Computes tasks assignment for pickers,
     * and returns list of pickers with assigned orders
     */
    List<Picker> assign();
}
