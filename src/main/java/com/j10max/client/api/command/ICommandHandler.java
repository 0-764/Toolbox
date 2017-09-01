package com.j10max.client.api.command;

import com.j10max.client.api.Initializer;

import java.util.List;

public interface ICommandHandler extends Initializer {

    void addCommand(ICommand command);

    void removeCommand(ICommand command);

    ICommand getCommandByPrefix(String prefix);

    boolean checkCommandExists(String prefix);

    List<ICommand> getCommandsList();

}
