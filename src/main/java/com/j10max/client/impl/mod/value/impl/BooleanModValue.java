package com.j10max.client.impl.mod.value.impl;

import com.j10max.client.impl.mod.value.ModValue;

public class BooleanModValue extends ModValue<Boolean> {

    public BooleanModValue(String name, Boolean value) {
        super(name, value);
    }

    @Override
    public void onParseValue(String fileContent) {
        this.setValue(Boolean.valueOf(fileContent));
    }

    @Override
    public String onWriteValue() {
        return this.getValue().toString();
    }

}
