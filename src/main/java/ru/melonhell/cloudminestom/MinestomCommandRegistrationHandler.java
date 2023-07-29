package ru.melonhell.cloudminestom;

import cloud.commandframework.Command;
import cloud.commandframework.internal.CommandRegistrationHandler;
import ru.melonhell.cloudminestom.utils.SetupUtils;
import org.jetbrains.annotations.NotNull;

public class MinestomCommandRegistrationHandler<C> implements CommandRegistrationHandler {

    private MinestomCommandManager<C> commandManager;

    void initialize(final @NotNull MinestomCommandManager<C> commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public boolean registerCommand(@NotNull Command<?> command) {
        SetupUtils.setup(commandManager, (Command<C>) command);
        return true;
    }
}
