package com.j10max.client.api.mod;

public enum ModType {

    PLAYER("Player"), WORLD("World"), ONLINE("Online"), INGAME("In-game"), MISC("Misc");

    private String name;

    ModType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
