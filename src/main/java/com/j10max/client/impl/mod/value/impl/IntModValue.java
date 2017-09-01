package com.j10max.client.impl.mod.value.impl;

import com.j10max.client.impl.mod.value.ModValue;

public class IntModValue extends ModValue<Integer> {

    public IntModValue(String name, Integer value) {
        super(name, value);
    }

    @Override
    public void onParseValue(String fileContent) {
        this.setValue(Integer.valueOf(fileContent));
    }

    @Override
    public String onWriteValue() {
        return "" + this.getValue();
    }
}
