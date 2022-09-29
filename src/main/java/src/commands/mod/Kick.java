package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Kick extends ListenerAdapter {
    /**
     * When a slash command with the name kick is used this method is called
     * it kicks the user given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.KICK_MEMBERS)){
            return;
        }
        if (event.getName().equals("kick")) {
            event.reply(event.getOption("user").getAsUser().getAsTag() + " has been kicked").queue(); // reply immediately
            User user = event.getOption("user").getAsUser();
            event.getGuild().kick(user).queue();
        }
    }
}
