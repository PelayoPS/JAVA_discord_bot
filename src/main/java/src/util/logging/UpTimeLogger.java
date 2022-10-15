package src.util.logging;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.ReadyEvent;

import java.util.concurrent.TimeUnit;

public class UpTimeLogger implements Logger<ReadyEvent> {

    private static long startTime = 0;
    private final JDA bot;


    public UpTimeLogger(JDA bot) {
        startTime = System.currentTimeMillis();
        this.bot = bot;
    }

    /**
     * Logs to the console that the bot is ready.
     * @param event
     * @param isProduction
     */
    @Override
    public void logEvent(ReadyEvent event, boolean isProduction) {
        System.out.println("Bot is ready!" + (isProduction ? " (Production mode)" : " (Development mode)"));
    }

    /**
     * Returns the current uptime of the bot
     * @return
     */
    public static String upTime() {
        long currentTime = System.currentTimeMillis();
        long upTime = currentTime - startTime;
        String time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(upTime),
                TimeUnit.MILLISECONDS.toMinutes(upTime) % TimeUnit.HOURS.toMinutes(1),
                TimeUnit.MILLISECONDS.toSeconds(upTime) % TimeUnit.MINUTES.toSeconds(1));
        return "Uptime: " + time;
    }
}
