package com.j10max.client.api.mod.value;

public interface IModValue<S> {

    void parseValue(String fileContent);

    String writeValue();

    void setName(String name);

    String getName();

    void setValue(S value);

    S getValue();

}
