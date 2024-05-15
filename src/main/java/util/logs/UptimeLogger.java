package util.logs;


import net.dv8tion.jda.api.events.session.ReadyEvent;

public class UptimeLogger implements Logger<ReadyEvent> {

    private static long startTime = 0;

    /**
     * Constructor for the UptimeLogger class.
     * 
     * @param time The time the bot started.
     */
    public UptimeLogger(long time) {
        startTime = time;
    }

    /**
     * Logs to the console that the bot is ready.
     * 
     * @param event
     * @param isProduction
     */
    @Override
    public void logEvent(ReadyEvent event) {
        System.out.println("\u001B[35mBot is ready!\u001B[0m");
    }

    /**
     * Returns the current uptime of the bot
     * 
     * @return
     */
    public static String upTime() {
        // gets current time
        long currentTime = System.currentTimeMillis();
        // calculates uptime based on the time of the onReady event
        long upTime = currentTime - startTime;
        // gets the values needed for the format
        long seconds = upTime / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
    
        seconds %= 60;
        minutes %= 60;
        // formats the string
        return String.format("%02dhours:%02dmins:%02dseconds", hours, minutes, seconds);
    }

}
