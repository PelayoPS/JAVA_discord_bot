package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Repeat implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "repeat";

    private final Category category = Category.GENERAL;

    private String description = "Repeats the text you give it in the channel you use say";

    // ====================CONSTRUCTOR SECTION====================//

    public Repeat() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name repeat is used this method is called
     * it repeats the content given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.getOption("channel").getAsChannel().asTextChannel().sendMessage(event.getOption("content").getAsString()).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Repeats the content given")
                .addOption(OptionType.STRING, "content", "The content to repeat", true)
                .addOption(OptionType.CHANNEL, "channel", "The channel to send the message to", true);
        return command;
    }

    /**
     * gets the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the name of the command for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * gets the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * gets the description of the command
     * @return
     */
    @Override
    public String getHelp() {
        return description;
    }
}
