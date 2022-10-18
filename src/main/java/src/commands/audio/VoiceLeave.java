package src.commands.audio;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class VoiceLeave implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "voiceleave";
    private final Category category = Category.AUDIO;

    private final String description = "Leaves the voice channel";

    // ====================CONSTRUCTOR SECTION====================//

    public VoiceLeave() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name leave is used this method is called
     * this command makes the bot leave the voice channel it is in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        Channel channel = Objects.requireNonNull(event.getGuild()).getAudioManager().getConnectedChannel();
        event.getGuild().getAudioManager().closeAudioConnection();
        event.reply("Left " + Objects.requireNonNull(channel).getName()).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * Returns the command data for the command
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
     * Returns the name to be used in the command manager
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
