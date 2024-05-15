package slash_commands.user_related;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.SlashCommand;

public class Ping implements SlashCommand {

    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("ping", "Description: Shows the latency of the.");
        return result;
    }

    public void execute(SlashCommandInteractionEvent event) {
        long latency = event.getJDA().getGatewayPing();
        event.reply("Pong! Latency: " + latency + "ms").queue();
        event.getChannel().sendMessage("hey");
    }

}