package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.Main;

import javax.security.auth.login.LoginException;


public class Restart extends ListenerAdapter {
    /**
     * When a slash command with the name restart is used this method is called
     * it restarts the bot
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("restart")) {
            if(!event.getMember().getPermissions().contains(Permission.ADMINISTRATOR)){
                return;
            }
            event.reply("Restarting...").setEphemeral(true).queue();
            try {
                Main.bot = new DiscordBot();//creates a new instance of the bot
            } catch (LoginException e) {
                System.out.println("ERROR: Provided bot token is invalid");// prints the error message
            }
        }
    }
}
