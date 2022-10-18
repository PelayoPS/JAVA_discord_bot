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

public class Ban implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "ban";

    private final Category category = Category.MOD;

    private final String description = "Bans the user given";

    // ====================CONSTRUCTOR SECTION====================//

    public Ban() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name ban is used this method is called
     * it bans the user given and sends a message to the channel the command was used in
     * @param event the event called
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.BAN_MEMBERS)){
            return;
        }
        event.reply(Objects.requireNonNull(event.getOption("user")).getAsUser().getAsTag() + " has been banned").queue(); // reply immediately
        User user = Objects.requireNonNull(event.getOption("user")).getAsUser();
        //noinspection ConstantConditions
        Objects.requireNonNull(event.getGuild()).ban(user, 0, null).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * @return the command data
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "user", "The user to ban", true);
    }

    /**
     * @return the name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the name of the command for management
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * @return the category of the command
     */
    public Category getCategory() {
        return category;
    }

    /**
     * @return the description of the command
     */
    @Override
    public String getHelp() {
        return description;
    }
}
