package src.commands.util;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Invoker extends ListenerAdapter {
    private CommandManager commandManager;

    public Invoker(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commandManager.getCommand(event.getName()).handle(event);
    }
}
