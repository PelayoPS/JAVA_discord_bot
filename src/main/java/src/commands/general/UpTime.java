package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;
import src.util.logging.UpTimeLogger;

public class UpTime implements CommandInterface {

    private static String name = "uptime";

    private Category category = Category.GENERAL;

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply(UpTimeLogger.upTime()).queue();
    }

    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Get the uptime of the bot");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public static String getNameForManagement(){
        return name;
    }
}
