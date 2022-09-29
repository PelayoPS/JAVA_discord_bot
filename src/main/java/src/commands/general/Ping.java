package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ping extends ListenerAdapter {
    /**
     * When a slash command with the name ping is used this method is called
     * it sends a message to the channel showing the ping of the bot
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ping")) {
            event.reply("Pong! " + event.getJDA().getGatewayPing() + "ms").queue();
        }
    }
}
