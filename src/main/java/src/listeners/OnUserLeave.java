package src.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import src.DiscordBot;
import src.listeners.serverStats.MemberStats;
import src.util.logging.UserLeaveLogger;

public class OnUserLeave extends ListenerAdapter {
    public OnUserLeave() {
    }

    /**
     * This method is called by JDA when a user leaves a guild.
     * Prints a message to the console when a user leaves a guild.
     */
    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {
        new UserLeaveLogger().logEvent(event, DiscordBot.isProductionEnabled());
        MemberStats.updateStats(event.getGuild());
    }
}
