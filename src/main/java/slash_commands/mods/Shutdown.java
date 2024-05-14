package slash_commands.mods;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.ISlashCommand;

/**
 * Represents a slash command that shuts down the bot.
 */
public class Shutdown implements ISlashCommand {

    /**
     * Retrieves the slash command information.
     *
     * @return The SlashCommandData object containing the command name and
     *         description.
     */
    @Override
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("shutdown", "Description: Shuts down the bot.")
        .setDefaultPermissions(DefaultMemberPermissions.enabledFor(Permission.ADMINISTRATOR));
        return result;
    }

    /**
     * Executes the slash command.
     *
     * @param event The SlashCommandInteractionEvent representing the interaction
     *              event.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Shutting down...").queue();
        event.getJDA().shutdown();
    }
}