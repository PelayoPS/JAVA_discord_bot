package src.util.logging;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import src.DiscordBot;

public class MessageLogger implements Logger<MessageReceivedEvent> {

    private DiscordBot bot;

    public MessageLogger(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    public void logEvent(MessageReceivedEvent event, boolean isDebug) {
        if (isDebug) {
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
        String textLogChannelId = bot.getConfig().get("TEXTLOGCHANELLID");
        String dmLogChannelId = bot.getConfig().get("DMLOGCHANNELID");
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
