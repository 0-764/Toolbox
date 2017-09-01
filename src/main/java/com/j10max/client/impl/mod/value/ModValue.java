package com.j10max.client.impl.mod.value;

import com.j10max.client.api.mod.value.IModValue;

public abstract class ModValue<S> implements IModValue<S> {

    private String name;
    private S value;

    public ModValue(String name, S value) {
        this.name = name;
        this.value = value;
    }

    public abstract void onParseValue(String fileContent);

    public abstract String onWriteValue();

    @Override
    public void parseValue(String fileContent) {
        this.onParseValue(fileContent);
    }

    @Override
    public String writeValue() {
        return this.onWriteValue();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setValue(S value) {
        this.value = value;
    }

    @Override
    public S getValue() {
        return null;
    }

}
