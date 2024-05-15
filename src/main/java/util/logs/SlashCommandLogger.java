package util.logs;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

/**
 * This class is responsible for logging slash command events.
 */
public class SlashCommandLogger implements Logger<SlashCommandInteractionEvent> {

    /**
     * Logs the slash command event.
     *
     * @param event The slash command interaction event to be logged.
     */
    @Override
    public void logEvent(SlashCommandInteractionEvent event) {
        System.out.print("\u001B[32m[LOG] ");
        // print in normal color again
        System.out.println("\u001B[0mSlash command event: " + event.getName() +
                " was triggered by " + event.getUser().getName() +
                 " in " + event.getGuild().getName() + ".");
    }

}