package src.util.logging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import src.DiscordBot;

public class SlashCommandLogger implements Logger<SlashCommandInteractionEvent> {

    public SlashCommandLogger() {
    }

    /**
     * Logs the slash command to the channel specified in the config file.
     */
    @Override
    public void logEvent(SlashCommandInteractionEvent event, boolean isProduction) {
        if (!isProduction) {
            return;
        }
        /*
         * Sends a message to the COMMANDLOGCHANNELID with the format:
         * [command] [user] [channel] [guild] [time] [command completed]
         */
        String channelID = DiscordBot.getConfig().get("COMMANDLOGCHANNELID");
        event.getJDA().getTextChannelById(channelID)
                .sendMessageEmbeds(
                    new EmbedBuilder()
                        .setTitle("Slash command used")
                        .addField("Command name", event.getName(), true)
                        .addField("User", event.getUser().getAsTag(), true)
                        .addField("Channel", event.getChannel().getName(), true)
                        .addField("Guild", event.getGuild().getName(), true)
                        .addField("Time", event.getTimeCreated().toString(), true)
                        .addField("Command", event.getCommandString(), true)
                        .build()
                ).queue();
    }
}
