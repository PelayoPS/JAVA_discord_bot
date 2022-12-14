package src.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import src.DiscordBot;
import src.util.logging.MessageLogger;

public class OnMessageReceived extends ListenerAdapter {
    private final DiscordBot bot;
    public OnMessageReceived(DiscordBot bot) {
        this.bot = bot;
    }

    /**
     * This method is called by JDA when a message is received.
     * Prints a message to the console when a message is received.
     * Logs the message to the text logs channel.
     * If the message is a private message logs it to the private logs channel.
     *
     */
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        new MessageLogger(bot).logEvent(event, DiscordBot.isProductionEnabled());
    }
}
