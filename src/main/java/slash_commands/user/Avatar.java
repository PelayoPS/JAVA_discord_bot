package slash_commands.user;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.ISlashCommand;

/**
 * Represents a slash command for retrieving the avatar of a user.
 */
public class Avatar implements ISlashCommand {

    /**
     * Retrieves the slash command information.
     *
     * @return The slash command data.
     */
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("avatar",
                "Description: Shows the avatar of the user provided, default user is the caller.");
        addOptions(result);
        return result;
    }

    /**
     * Adds options to the slash command.
     *
     * @param slashCommandData The slash command data to add options to.
     */
    public void addOptions(SlashCommandData slashCommandData) {
        slashCommandData.addOption(OptionType.USER, "user",
                "The user to get the avatar from.", false);

    }

    /**
     * Executes the slash command.
     *
     * @param event The slash command interaction event.
     */
    public void execute(SlashCommandInteractionEvent event) {
        User user = event.getOption("user") == null ? event.getUser()
                : event.getOption("user").getAsUser();
        event.reply(user.getAvatarUrl()).queue();
    }

}