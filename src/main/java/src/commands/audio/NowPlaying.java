package src.commands.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class NowPlaying implements CommandInterface {

// ====================VARIABLES SECTION====================//

    private static final String name = "nowplaying";
    private final Category category = Category.AUDIO;

    private final String description = "Shows the current song";

    // ====================CONSTRUCTOR SECTION====================//

    public NowPlaying() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * returns the info related to the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        AudioTrackInfo trackInfo = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).getAudioPlayer().getPlayingTrack().getInfo();
        event.reply(trackInfo.title+trackInfo.author+" -> "+trackInfo.uri).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
    }

    /**
     * returns the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * returns name for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * returns the description of the command
     * @return
     */
    @Override
    public String getHelp() {
        return description;
    }
}
