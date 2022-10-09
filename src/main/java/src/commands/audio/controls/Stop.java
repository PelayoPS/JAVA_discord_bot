package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Stop implements CommandInterface {

    private static String name = "stop";
    private Category category = Category.AUDIO;
    public Stop() {
    }

    /**
     * Stops the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.getGuild().getAudioManager().closeAudioConnection();
        event.reply("Left the voice channel").queue();
    }

    /**
     * Returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash("stop", "Leaves the voice channel");
    }

    /**
     * Returns the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * Returns the name of the command for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }
}
