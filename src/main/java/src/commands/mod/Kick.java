package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Kick implements CommandInterface {

    private static final String name = "kick";

    private final Category category = Category.MOD;

    /**
     * When a slash command with the name kick is used this method is called
     * it kicks the user given and sends a message to the channel the command was used in
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.KICK_MEMBERS)){
            return;
        }
        event.reply(event.getOption("user").getAsUser().getAsTag() + " has been kicked").queue(); // reply immediately
        User user = event.getOption("user").getAsUser();
        event.getGuild().kick(user).queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Kicks the user given")
                .addOption(OptionType.USER, "user", "The user to kick", true);
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
}
