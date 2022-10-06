package src.util.logging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import src.DiscordBot;

public class SlashCommandLogger implements Logger<SlashCommandInteractionEvent> {

    public SlashCommandLogger() {
    }

    @Override
    public void logEvent(SlashCommandInteractionEvent event) {
        /*
         * Sends a message to the COMMANDLOGCHANNELID with the format:
         * [COMMANDNAME] was used by [USER] in [CHANNEL] in [GUILD] at [TIME] with response [RESPONSE]
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
