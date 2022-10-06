package src.util.commandPattern;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.util.logging.SlashCommandLogger;

public class Invoker extends ListenerAdapter {
    private CommandManager commandManager;

    public Invoker(CommandManager manager) {
        this.commandManager = manager;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        CommandInterface command = commandManager.getCommand(event.getName());
        if(event.getMember().getPermissions().contains(command.getCategory().getPermission())) {
            command.handle(event);
        } else {
            event.reply("You don't have permission to use this command!").setEphemeral(true).queue();
        }
        new SlashCommandLogger().logEvent(event, DiscordBot.isProductionEnabled());
    }
}
