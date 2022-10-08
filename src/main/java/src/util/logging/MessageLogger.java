package src.util.logging;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.DiscordBot;

public class MessageLogger implements Logger<MessageReceivedEvent> {

    private final DiscordBot bot;

    public MessageLogger(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    /**
     * Logs a message to the channel specified in the config file.
     * @param event
     * @param isProduction
     */
    @Override
    public void logEvent(MessageReceivedEvent event, boolean isProduction) {
        if (!isProduction) {
            return;
        }
        /*
         * Logs the message to the text logs channel.
         * format:
         *  if message from server
         *     [channel] [username] [message]
         *  if message from private message
         *     [dm] [username] [message]
         */
        String textLogChannelId = DiscordBot.getConfig().get("TEXTLOGCHANELLID");
        String dmLogChannelId = DiscordBot.getConfig().get("DMLOGCHANNELID");
        if (event.getMessage().getAuthor().isBot()) {//messages from bots are ignored
            return;
        }
        if (event.isFromGuild()) {//if message is from a server
            event.getGuild().getTextChannelById(textLogChannelId)
                    .sendMessage("[" + event.getChannel().getAsMention() + "] [" + event.getAuthor().getAsMention() + "] " +
                            '"' + event.getMessage().getContentRaw()+ '"').queue();
        } else {//if message is from a private message
            bot.getJda().getTextChannelById(dmLogChannelId)//if you try to get channel as if it was from guild it will throw an exception
                    .sendMessage("[dm] [" + event.getAuthor().getAsTag() + "] " +
                            '"' + event.getMessage().getContentRaw() + '"').queue();
        }
    }
}
