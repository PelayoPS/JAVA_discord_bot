package src.commands.audio.controls;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.audio.lavaplayer.GuildMusicManager;
import src.commands.audio.lavaplayer.PlayerManager;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Volume implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static String name = "volume";
    private Category category = Category.AUDIO;

    private String description = "Sets the volume of the player (0-100)";

    // ====================CONSTRUCTOR SECTION====================//

    public Volume() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * Sets the volume of the current song
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        GuildMusicManager musicManager = PlayerManager.getInstance().getMusicManager(event.getGuild());
        if (musicManager == null) {
            event.reply("There is no song playing").queue();
        } else {
            int volume = event.getOption("volume").getAsInt();
            musicManager.setVolume(volume);
            event.reply("Volume set to " + volume).queue();
        }
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * Returns the slash command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description)
                .addOption(OptionType.INTEGER, "volume", "The volume to set", true);
        return commandData;
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
