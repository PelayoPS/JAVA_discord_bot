package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.GuildMusicManager;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class Volume implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "volume";
    private final Category category = Category.AUDIO;

    private final String description = "Sets the volume of the player (0-100)";

    // ====================CONSTRUCTOR SECTION====================//

    public Volume() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * Sets the volume of the current song
     * @param event the event called
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(Objects.requireNonNull(event.getGuild()));
        if (musicManager == null) {
            event.reply("There is no song playing").queue();
        } else {
            int volume = Objects.requireNonNull(event.getOption("volume")).getAsInt();
            musicManager.setVolume(volume);
            event.reply("Volume set to " + volume).queue();
        }
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * @return the slash command
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.INTEGER, "volume", "The volume to set", true);
    }

    /**
     * @return the name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the category of the command
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * @return the name of the command for management
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * @return the description of the command
     */
    @Override
    public String getHelp() {
        return description;
    }
}
