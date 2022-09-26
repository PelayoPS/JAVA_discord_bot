package src.commands.mod;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")) {
            event.reply(event.getOption("user").getAsUser().getAsTag() + " has been banned").queue(); // reply immediately
            User user = event.getOption("user").getAsUser();
            event.getGuild().ban(user, 0, null).queue();
        }
    }
}
