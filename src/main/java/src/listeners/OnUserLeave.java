package src.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.listeners.serverStats.MemberStats;
import src.util.logging.UserLeaveLogger;

public class OnUserLeave extends ListenerAdapter {
    private DiscordBot bot = null;
    public OnUserLeave(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    /**
     * This method is called by JDA when a user leaves a guild.
     * Prints a message to the console when a user leaves a guild.
     */
    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        new UserLeaveLogger(bot).logEvent(event, DiscordBot.isProductionEnabled());
        MemberStats.updateStats(event.getGuild());
    }
}
