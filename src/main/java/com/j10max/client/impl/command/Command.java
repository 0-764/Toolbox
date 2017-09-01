package com.j10max.client.impl.command;

import com.j10max.client.api.command.ICommand;

public class Command implements ICommand {

    private String[] prefixes;

    public Command(String[] prefixes) {
        this.prefixes = prefixes;
    }

    @Override
    public boolean executeCmd() {
        return false;
    }

    @Override
    public String[] getPrefixes() {
        return new String[0];
    }

}
