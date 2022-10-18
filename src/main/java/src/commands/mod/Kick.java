package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class Kick implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "kick";

    private final Category category = Category.MOD;

    private final String description = "Kicks the user given";

    // ====================CONSTRUCTOR SECTION====================//

    public Kick() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name kick is used this method is called
     * it kicks the user given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.KICK_MEMBERS)){
            return;
        }
        event.reply(Objects.requireNonNull(event.getOption("user")).getAsUser().getAsTag() + " has been kicked").queue(); // reply immediately
        User user = Objects.requireNonNull(event.getOption("user")).getAsUser();
        Objects.requireNonNull(event.getGuild()).kick(user).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "user", "The user to kick", true);
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
     * gets the name of the command for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
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
