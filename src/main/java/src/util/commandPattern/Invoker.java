package src.util.commandPattern;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.util.logging.SlashCommandLogger;

import java.util.Objects;

public class Invoker extends ListenerAdapter {
    private final CommandManager commandManager;

    public Invoker(CommandManager manager) {
        this.commandManager = manager;
    }

    /**
     * This method is called by JDA when a slash command is received.
     * if the permissions are correct, the command is executed.
     * logs the command to the channel after executing it
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        CommandInterface command = commandManager.getCommand(event.getName());
        if (command == null) {
            event.reply("Command not found").queue();
        }
        if(Objects.requireNonNull(event.getMember()).getPermissions().contains(Objects.requireNonNull(command).getCategory().getPermission())) {
            command.handle(event);
        } else {
            event.reply("You don't have permission to use this command!").setEphemeral(true).queue();
        }
        new SlashCommandLogger().logEvent(event, DiscordBot.isProductionEnabled());
    }
}
