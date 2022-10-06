package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class Help implements CommandInterface {

    private static String name = "help";

    private Category category = Category.GENERAL;

    /**
     * When a slash command with the name help is used this method is called
     * it sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        /*
         * replies with a discord embed message with two columns
         * firs one it for general commands
         * second one is for mod commands
         */
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle("Help")
                .setDescription("Here is a list of all the commands")
                .addField("General", getCommandList(event).toString(), false)
                .build();
        event.replyEmbeds(messageEmbed).queue(); // reply immediately
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Returns a list of commands");
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
     * gets a list of all the commands
     * @param event
     * @return
     */
    private List<String> getCommandList(SlashCommandInteractionEvent event) {
        List<String> commandList = new ArrayList<>();
        event.getJDA().retrieveCommands().complete()
                .forEach(command -> commandList.add(command.getName()));
        return commandList;
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
