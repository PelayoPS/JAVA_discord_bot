package src.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;

public class OnMessageReceived extends ListenerAdapter {
    private DiscordBot bot = null;
    public OnMessageReceived(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    /**
     * This method is called by JDA when a message is received.
     * Prints a message to the console when a message is received.
     * Logs the message to the text logs channel.
     * If the message is a private message logs it to the private logs channel.
     *
     */
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        /*
         * Logs the message to the text logs channel.
         * format:
         *  if message from server
         *     [channel] [username] [message]
         *  if message from private message
         *     [dm] [username] [message]
         */
        String textlogchannelid = bot.getConfig().get("TEXTLOGCHANELLID");
        String dmlogchannelid = bot.getConfig().get("DMLOGCHANNELID");
        if (event.getMessage().getAuthor().isBot()) {//messages from bots are ignored
            return;
        }
        if (event.isFromGuild()) {//if message is from a server
            event.getGuild().getTextChannelById(textlogchannelid)
                    .sendMessage("[" + event.getChannel().getAsMention() + "] [" + event.getAuthor().getAsMention() + "] " +
                            '"' + event.getMessage().getContentRaw()+ '"').queue();
        } else {//if message is from a private message
            bot.getShardManager().getTextChannelById(dmlogchannelid)//if you try to get channel as if it was from guild it will throw an exception
                    .sendMessage("[dm] [" + event.getAuthor().getAsTag() + "] " +
                            '"' + event.getMessage().getContentRaw() + '"').queue();
        }
    }
}
