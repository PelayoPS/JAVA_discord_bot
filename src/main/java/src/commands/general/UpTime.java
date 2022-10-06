package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;
import src.util.logging.UpTimeLogger;

public class UpTime implements CommandInterface {

    private static final String name = "uptime";

    private final Category category = Category.GENERAL;

    /**
     * When a slash command with the name uptime is used this method is called
     * it sends a message to the channel showing the uptime of the bot
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply(UpTimeLogger.upTime()).queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Get the uptime of the bot");
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
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * gets the category of the command
     * @return
     */
    public static String getNameForManagement(){
        return name;
    }
}
