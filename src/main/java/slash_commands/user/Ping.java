package slash_commands.user;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.Code;
import slash_commands.ISlashCommand;

/**
 * Represents the "ping" slash command.
 * This command shows the latency of the bot.
 */
public class Ping implements ISlashCommand {

    private final Code code = Code.USER;

    /* =========================SUPER METHODS=============================== */

    /**
     * Retrieves the slash command information.
     * 
     * @return The slash command data.
     */
    @Override
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("ping", "Description: Shows the latency of the bot.");
        return result;
    }

    /**
     * Executes the "ping" slash command.
     * 
     * @param event The slash command interaction event.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        long latency = event.getJDA().getGatewayPing();
        event.reply("Pong! Latency: " + latency + "ms").queue();
        event.getChannel().sendMessage("hey");
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

    /**
     * Retrieves the help information for the slash command.
     * 
     * @return A string containing information about how to use the command.
     */
    @Override
    public String getHelp() {
        return "Usage: /ping\n" + "Description: Shows the latency of the bot.";
    }
}