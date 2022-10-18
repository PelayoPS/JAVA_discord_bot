package src.commands.general.help;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;
import src.util.commandPattern.CommandManager;

import java.awt.*;
import java.util.List;
import java.util.*;

public class HelpMenuCreator {
    
    private static CommandManager commandManager;

    public static final Color helpColor = Color.BLUE;

    /**
     * creates a help menu
     * with the list of categories it creates a list of commands filtered by category
     * @param commandManager
     */
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public HelpMenuCreator(CommandManager commandManager) {
        HelpMenuCreator.commandManager = commandManager;
        List<Category> categories = Arrays.stream(Category.values()).toList();
        for (Category category : categories) {
            Map<Category, String> commands = new HashMap<>();
            commands.put(category, getMenu(category));
        }
    }

    /**
     * returns the default help message
     * @return
     */
    public MessageEmbed getHelpEmbed() {
        EmbedBuilder messageEmbedBuilder = new EmbedBuilder()
                .setAuthor("Help")
                .setDescription("To use this menu click on the buttons below to select a category")
                .setColor(helpColor);
        return messageEmbedBuilder.build();
    }

    /**
     * replies to the event with the help menu
     * adding an action row with buttons to switch between the categories
     * @param event
     */
    public void reply(SlashCommandInteractionEvent event) {
        ReplyCallbackAction replyCallbackAction = event.replyEmbeds(getHelpEmbed());
        List<ItemComponent> buttons = new ArrayList<>();
        for (Category category : getCategories()) {
            //if the user doesn't have permission to use the command it won't show up
            if (Objects.requireNonNull(event.getMember()).hasPermission(category.getPermission())) {
                buttons.add(Button.of(ButtonStyle.PRIMARY, category.name(), category.name()));
            }
        }
        replyCallbackAction.addActionRow(buttons).queue();
    }

    /**
     * returns the list of categories
     * @return
     */
    public static List<Category> getCategories() {
        return Arrays.stream(Category.values()).toList();
    }

    /**
     * returns the help menu for the commands in the category given
     * format: command name -> command description
     * @return
     */
    public static String getMenu(Category category) {
        List<String> commandsString = commandManager.getCommands(category);
        List<CommandInterface> commands = new ArrayList<>();
        for (String command : commandsString) {
            commands.add(commandManager.getCommand(command));
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (CommandInterface command : commands) {
            stringBuilder.append(command.getName()).append(" -> ").append(command.getHelp()).append("\n");
        }
        return stringBuilder.toString();
    }

}
