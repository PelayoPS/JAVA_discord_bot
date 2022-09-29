package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.Main;


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
                Main.bot.getJda().shutdown();//this makes the bot go offline so a new instance can be created
                restart();
            }catch (Exception e) {
            }
        }
    }

    public void restart() throws Exception {
        try
        {
            throw new Exception("Force restart!");
        }
        finally
        {
            Main.main(new String[0]);
        }
    }
}
