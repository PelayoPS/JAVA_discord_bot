package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.commandUpdaters.GeneralCommandUpdater;
import src.commandUpdaters.ModCommandUpdater;

public class Help extends ListenerAdapter {
    /**
     * When a slash command with the name help is used this method is called
     * it sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("help")) {
            /*
             * replies with a discord embed message with two columns
             * firs one it for general commands
             * second one is for mod commands
             */
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setTitle("Help")
                    .setDescription("Here is a list of all the commands")
                    .addField("General", GeneralCommandUpdater.getGeneralCommands().toString(), false)
                    .addField("Mod", ModCommandUpdater.getModerationCommands().toString(), false)
                    .build();
        }
    }
}
