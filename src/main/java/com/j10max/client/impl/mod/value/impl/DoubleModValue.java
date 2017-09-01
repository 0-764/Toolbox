package com.j10max.client.impl.mod.value.impl;

import com.j10max.client.impl.mod.value.ModValue;

public class DoubleModValue extends ModValue<Double> {

    public DoubleModValue(String name, Double value) {
        super(name, value);
    }

    @Override
    public void onParseValue(String fileContent) {
        this.setValue(Double.valueOf(fileContent));
    }

    @Override
    public String onWriteValue() {
        return "" + this.getValue();
    }

}
