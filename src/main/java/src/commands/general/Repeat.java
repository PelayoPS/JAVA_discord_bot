package src.commands.general;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.util.Category;
import src.commands.util.CommandInterface;

public class Repeat implements CommandInterface {

    private static String name = "repeat";

    private Category category = Category.GENERAL;

    /**
     * When a slash command with the name repeat is used this method is called
     * it repeats the content given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply(event.getOption("content").getAsString()).queue(); // reply immediately
    }

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Repeats the content given")
                .addOption(OptionType.STRING, "content", "The content to repeat", true);
        return command;
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
