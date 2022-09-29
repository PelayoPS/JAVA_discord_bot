package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Ban extends ListenerAdapter {
    /**
     * When a slash command with the name ban is used this method is called
     * it bans the user given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")) {
            if(!event.getMember().getPermissions().contains(Permission.BAN_MEMBERS)){
                return;
            }
            event.reply(event.getOption("user").getAsUser().getAsTag() + " has been banned").queue(); // reply immediately
            User user = event.getOption("user").getAsUser();
            event.getGuild().ban(user, 0, null).queue();
        }
    }
}
