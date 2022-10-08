package src.commands.audio;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class VoiceJoin implements CommandInterface {

    private static String name = "voicejoin";
    private Category category = Category.AUDIO;

    public VoiceJoin() {
    }

    /**
     * When a slash command with the name join is used this method is called
     * this command makes the bot join the voice channel the user is in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.getMember().getVoiceState().getChannel().getGuild().getAudioManager().openAudioConnection(event.getMember().getVoiceState().getChannel());
        event.reply("Joined " + event.getMember().getVoiceState().getChannel().getName()).queue();
    }

    /**
     * Returns the command data for the command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, "Joins the voice channel you are in.");
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
