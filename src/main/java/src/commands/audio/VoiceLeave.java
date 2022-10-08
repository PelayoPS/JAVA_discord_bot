package src.commands.audio;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class VoiceLeave implements CommandInterface {

    private static String name = "voiceleave";
    private Category category = Category.AUDIO;

    public VoiceLeave() {
    }

    /**
     * When a slash command with the name leave is used this method is called
     * this command makes the bot leave the voice channel it is in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.getGuild().getAudioManager().closeAudioConnection();
        event.reply("Left " + event.getMember().getVoiceState().getChannel()).queue();
    }

    /**
     * Returns the command data for the command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash("voiceleave", "Leaves the voice channel you are in.");
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
}
