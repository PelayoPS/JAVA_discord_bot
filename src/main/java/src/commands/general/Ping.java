package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Ping implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "ping";

    private final Category category = Category.GENERAL;

    private final String description = "Returns the bot's ping";

    // ====================CONSTRUCTOR SECTION====================//

    public Ping() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name ping is used this method is called
     * it sends a message to the channel showing the ping of the bot
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("Pong! " + event.getJDA().getGatewayPing() + "ms").queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
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
