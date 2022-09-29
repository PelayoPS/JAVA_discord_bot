package src.commands.mod;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import src.DiscordBot;

public class Mute extends ListenerAdapter {

    Dotenv config = DiscordBot.getConfig();
    /**
     * When a slash command with the name mute is used this method is called
     * it mutes the user given for the amount of time given
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        //if the user is not admin then return
        if(!event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)){
            return;
        }
        if (event.getName().equals("mute")) {
            muteUser(event.getOption("user"), event.getOption("time"));
            logMute(event, event.getOption("user"), event.getOption("time"));
        }
    }

    /**
     * private method to make the code cleaner
     * @param user
     * @param time
     */
    private void muteUser(OptionMapping user, OptionMapping time) {
        /*
         * sends a message to the user saying they have been muted
         * gives the user the muted role
         * removes the muted role after the time given
         */
        user.getAsUser().openPrivateChannel().queue((channel) -> {
            channel.sendMessage("You have been muted for " + time.getAsLong() + " seconds").queue();
        });
        //muted rol
        Role role = user.getAsMember().getGuild().getRolesByName("muted",true).get(0);
        //adds muted role
        user.getAsMember().getGuild()
            .addRoleToMember(user.getAsMember(), role).queue();
        //removes muted role after time given
        user.getAsMember().getGuild()
            .removeRoleFromMember(user.getAsMember(), role)
                .queueAfter(time.getAsLong(), java.util.concurrent.TimeUnit.SECONDS);
    }

    private void logMute(SlashCommandInteractionEvent event, OptionMapping user, OptionMapping time) {
        /*
         * sends a message to the log channel saying who muted who for how long
         */
        event.getGuild().getTextChannelById(config.get("MUTEDLOGCHANNELID"))
            .sendMessage(user.getAsUser().getName() +
                    " was muted by " + event.getUser().getName() +
                    " for " + time.getAsLong() + " seconds").queue();
    }
}
