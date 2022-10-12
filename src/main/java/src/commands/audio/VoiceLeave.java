package src.commands.audio;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class VoiceLeave implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static String name = "voiceleave";
    private Category category = Category.AUDIO;

    private String description = "Leaves the voice channel";

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
        Channel channel = event.getGuild().getAudioManager().getConnectedChannel();
        event.getGuild().getAudioManager().closeAudioConnection();
        event.reply("Left " + channel.getName()).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * Returns the command data for the command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description);
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
