package src.commands.audio.controls;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Next implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static String name = "next";
    private Category category = Category.AUDIO;
    private String description = "Skips the current song";

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
        for (int amount = event.getOption("amount").getAsInt(); amount > 0; amount--) {
            PlayerManager.getInstance().getMusicManager(event.getGuild()).scheduler.nextTrack();
        }
        AudioTrackInfo trackInfo = PlayerManager.getInstance().getMusicManager(event.getGuild()).getAudioPlayer().getPlayingTrack().getInfo();
        event.reply(trackInfo.title+trackInfo.author+" -> "+trackInfo.uri).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description)
                .addOption(OptionType.INTEGER, "amount", "The amount of songs to skip", true);
        return commandData;
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
