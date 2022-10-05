package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.util.Category;
import src.commands.util.CommandInterface;

public class Ban implements CommandInterface {

    private static String name = "ban";

    private Category category = Category.MOD;

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

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Bans the user given")
                .addOption(OptionType.USER, "user", "The user to ban", true);
        return command;
    }

    @Override
    public String getName() {
        return name;
    }

    public static String getNameForManagement() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
