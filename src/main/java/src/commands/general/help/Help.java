package src.commands.general.help;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;
import src.util.commandPattern.CommandManager;

public class Help implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "help";

    private final CommandManager commandManager;

    private final Category category = Category.GENERAL;

    private String description = "Shows all commands";

    // ====================CONSTRUCTOR SECTION====================//

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name help is used this method is called
     * it sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        HelpMenuCreator helpMenuCreator = new HelpMenuCreator(commandManager);
        helpMenuCreator.reply(event);
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
