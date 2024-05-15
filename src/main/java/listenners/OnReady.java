package listenners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.logs.UptimeLogger;

public class OnReady extends ListenerAdapter {

    /**
     * This method is called by JDA when the bot is ready to start receiving events.
     * Prints a message to the console when the bot is ready.
     */
    @Override
    public void onReady(ReadyEvent event) {
        long time = System.currentTimeMillis();
        new UptimeLogger(time).logEvent(event);
    }

}
