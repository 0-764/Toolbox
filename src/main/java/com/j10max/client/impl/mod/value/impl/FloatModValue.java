package com.j10max.client.impl.mod.value.impl;

import com.j10max.client.impl.mod.value.ModValue;

public class FloatModValue extends ModValue<Float> {

    public FloatModValue(String name, Float value) {
        super(name, value);
    }

    @Override
    public void onParseValue(String fileContent) {
        this.setValue(Float.valueOf(fileContent));
    }

    @Override
    public String onWriteValue() {
        return "" + this.getValue();
    }
}
