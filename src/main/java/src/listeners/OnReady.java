package src.listeners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import src.DiscordBot;
import src.util.logging.UpTimeLogger;

public class OnReady extends ListenerAdapter {
    /**
     * This method is called by JDA when the bot is ready to start receiving events.
     * Prints a message to the console when the bot is ready.
     */
    @Override
    public void onReady(@NotNull ReadyEvent event) {
        new UpTimeLogger().logEvent(event, DiscordBot.isProductionEnabled());
    }

}
