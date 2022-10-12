package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Pause implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static String name = "pause";
    private Category category = Category.AUDIO;

    private String description = "Pauses the current song";

    // ====================CONSTRUCTOR SECTION====================//

    public Pause() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * Pauses the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer().setPaused(true);
        event.reply("Paused").queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * Returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
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

    /**
     * Returns the description of the command
     * @return
     */
    @Override
    public String getHelp() {
        return description;
    }
}
