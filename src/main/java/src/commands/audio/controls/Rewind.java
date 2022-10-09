package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Rewind implements CommandInterface {

    private static String name = "rewind";
    private Category category = Category.AUDIO;
    public Rewind() {
    }

    /**
     * Replays the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
            PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer()
                    .playTrack(PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer().getPlayingTrack().makeClone());
    }

    /**
     * Returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Rewinds the current song");
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
