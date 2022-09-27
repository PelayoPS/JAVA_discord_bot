package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Repeat extends ListenerAdapter {
    /**
     * When a slash command with the name repeat is used this method is called
     * it repeats the content given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("repeat")) {
            event.reply(event.getOption("content").getAsString()).queue(); // reply immediately
        }
    }
}
