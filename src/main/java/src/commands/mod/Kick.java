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

    private static String name = "kick";

    private Category category = Category.MOD;

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

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Kicks the user given")
                .addOption(OptionType.USER, "user", "The user to kick", true);
        return command;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public static String getNameForManagement() {
        return name;
    }
}
