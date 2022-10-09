package src.commands.audio;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class NowPlaying implements CommandInterface {

    private static String name = "nowplaying";
    private Category category = Category.AUDIO;
    public NowPlaying() {
    }

    /**
     * returns the info related to the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        AudioTrackInfo trackInfo = PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer().getPlayingTrack().getInfo();
        event.reply(trackInfo.title+trackInfo.author+" -> "+trackInfo.uri).queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Shows the current song");
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
}
