package slash_commands.mods;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.Code;
import slash_commands.ISlashCommand;

/**
 * Represents a slash command that shuts down the bot.
 */
public class Shutdown implements ISlashCommand {
    private final Code code = Code.ADMIN;

    /* =========================SUPER METHODS=============================== */

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
        event.reply("Shutting down...").setEphemeral(true).queue();
        event.getJDA().shutdown();
    }

    /**
     * Retrieves the help information for the slash command.
     *
     * @return A string containing information about how to use the command.
     */
    @Override
    public String getHelp() {
        return "Usage: /shutdown\n" + "Description: Shuts down the bot."
                + "This command can only be used by administrators.";

    }

    /**
     * Retrieves the command code.
     *
     * @return The command code.
     */
    @Override
    public Code getCode() {
        return code;
    }
}