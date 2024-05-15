package slash_commands.mods;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.Code;
import slash_commands.ISlashCommand;
import util.logs.UptimeLogger;

/**
 * Represents a slash command that shows the time the bot has been active.
 */
public class Uptime implements ISlashCommand {

    private Code code = Code.MOD;

    /* =========================SUPER METHODS=============================== */

    /**
     * Retrieves the slash command information for the uptime command.
     *
     * @return The slash command data.
     */
    @Override
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("uptime", "Shows the time the bot has been active");
        return result;
    }

    /**
     * Executes the uptime command and sends a message with the bot's active time.
     *
     * @param event The slash command interaction event.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String uptime = UptimeLogger.upTime();
        event.reply("The bot has been active for " + uptime).queue();
    }

    /**
     * Retrieves the help message for the uptime command.
     *
     * @return The help message.
     */
    @Override
    public String getHelp() {
        return "Usage: /uptime\n" + "Description: Shows the time the bot has been active";
    }

    /**
     * Retrieves the code for the uptime command.
     *
     * @return The code.
     */
    @Override
    public Code getCode() {
        return code;
    }

}
