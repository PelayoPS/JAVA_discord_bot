package slash_commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

/**
 * The SlashCommand interface represents a slash command in a Discord bot.
 * Slash commands are used to create custom commands that can be executed by
 * users in a Discord server.
 */
public interface ISlashCommand {

    /**
     * Retrieves the slash command information.
     * This method should be implemented to return the SlashCommandData object that
     * represents the command's data.
     * 
     * @return The SlashCommandData object representing the command's data.
     */
    public SlashCommandData getSlashInfo();

    /**
     * Executes the slash command.
     * This method should be implemented to handle the execution of the command when
     * it is triggered by a user.
     * 
     * @param event The SlashCommandInteractionEvent object representing the
     *              interaction event triggered by the user.
     */
    public void execute(SlashCommandInteractionEvent event);
}
