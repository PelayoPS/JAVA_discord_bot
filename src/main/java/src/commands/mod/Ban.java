package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Ban implements CommandInterface {

    private static final String name = "ban";

    private final Category category = Category.MOD;

    /**
     * When a slash command with the name ban is used this method is called
     * it bans the user given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.BAN_MEMBERS)){
            return;
        }
        event.reply(event.getOption("user").getAsUser().getAsTag() + " has been banned").queue(); // reply immediately
        User user = event.getOption("user").getAsUser();
        event.getGuild().ban(user, 0, null).queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Bans the user given")
                .addOption(OptionType.USER, "user", "The user to ban", true);
        return command;
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
    public Category getCategory() {
        return category;
    }
}
