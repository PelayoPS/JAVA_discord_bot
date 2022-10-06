package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Ping implements CommandInterface {

    private static String name = "ping";

    private Category category = Category.GENERAL;

    /**
     * When a slash command with the name ping is used this method is called
     * it sends a message to the channel showing the ping of the bot
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("Pong! " + event.getJDA().getGatewayPing() + "ms").queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Returns the ping of the bot");
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
}
