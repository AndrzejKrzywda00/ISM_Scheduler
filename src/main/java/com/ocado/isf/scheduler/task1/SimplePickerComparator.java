package com.ocado.isf.scheduler.task1;

import com.ocado.isf.model.Picker;

import java.util.Comparator;

/***
 * Comparing pickers by the remaining duration, decreasing.
 */
public class SimplePickerComparator implements Comparator<Picker> {
    @Override
    public int compare(Picker p1, Picker p2) {
        return p2.currentStartingTime().compareTo(p1.currentStartingTime());
    }
}
