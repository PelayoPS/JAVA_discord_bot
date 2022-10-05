package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.util.Category;
import src.commands.util.CommandInterface;

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

    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Returns the ping of the bot");
    }

    @Override
    public String getName() {
        return name;
    }

    public static String getNameForManagement() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}
