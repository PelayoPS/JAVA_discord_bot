package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class Rewind implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "rewind";
    private final Category category = Category.AUDIO;

    private final String description = "Rewinds the current song";

    // ====================CONSTRUCTOR SECTION====================//

    public Rewind() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * Replays the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
            PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).getAudioPlayer()
                    .playTrack(PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer().getPlayingTrack().makeClone());
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
