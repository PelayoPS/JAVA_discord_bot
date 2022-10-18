package src.commands.audio.controls;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class Next implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "next";
    private final Category category = Category.AUDIO;
    private final String description = "Skips the current song";

    // ====================CONSTRUCTOR SECTION====================//

    public Next() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * when the slash command is called it skips the current song
     * and shows the info of the new one
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        for (int amount = Objects.requireNonNull(event.getOption("amount")).getAsInt(); amount > 0; amount--) {
            PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).scheduler.nextTrack();
        }
        AudioTrackInfo trackInfo = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild())).getAudioPlayer().getPlayingTrack().getInfo();
        event.reply(trackInfo.title+trackInfo.author+" -> "+trackInfo.uri).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.INTEGER, "amount", "The amount of songs to skip", true);
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
     * returns the name of the command for management
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
