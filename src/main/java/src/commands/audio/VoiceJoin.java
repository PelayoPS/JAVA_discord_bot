package src.commands.audio;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class VoiceJoin implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "voicejoin";
    private final Category category = Category.AUDIO;

    private final String description = "Joins the voice channel where the user is in";

    // ====================CONSTRUCTOR SECTION====================//

    public VoiceJoin() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name join is used this method is called
     * this command makes the bot join the voice channel the user is in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel() != null){
            event.getMember().getVoiceState().getChannel().getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
            event.reply("Joined " + event.getMember().getVoiceState().getChannel().getName()).queue();
        } else {
            event.reply("You are not in a voice channel").queue();
        }
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
    public String getHelp() {
        return description;
    }
}
