package src.listeners;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnReady extends ListenerAdapter {
    /**
     * This method is called by JDA when the bot is ready to start receiving events.
     * Prints a message to the console when the bot is ready.
     */
    @Override
    public void onReady(ReadyEvent event) {
        System.out.println("Bot is ready!");
    }

}
