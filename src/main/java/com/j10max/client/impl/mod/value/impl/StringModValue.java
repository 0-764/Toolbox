package com.j10max.client.impl.mod.value.impl;

import com.j10max.client.impl.mod.value.ModValue;

public class StringModValue extends ModValue<String> {

    public StringModValue(String name, String value) {
        super(name, value);
    }

    @Override
    public void onParseValue(String fileContent) {
        this.setValue(fileContent);
    }

    @Override
    public String onWriteValue() {
        return this.getValue();
    }

}
