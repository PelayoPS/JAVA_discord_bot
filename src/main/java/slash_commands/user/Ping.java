package slash_commands.user;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.ISlashCommand;

/**
 * Represents the "ping" slash command.
 * This command shows the latency of the bot.
 */
public class Ping implements ISlashCommand {

    /**
     * Retrieves the slash command information.
     * 
     * @return The slash command data.
     */
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("ping", "Description: Shows the latency of the bot.");
        return result;
    }

    /**
     * Executes the "ping" slash command.
     * 
     * @param event The slash command interaction event.
     */
    public void execute(SlashCommandInteractionEvent event) {
        long latency = event.getJDA().getGatewayPing();
        event.reply("Pong! Latency: " + latency + "ms").queue();
        event.getChannel().sendMessage("hey");
    }

}