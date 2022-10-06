package src.listeners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.util.logging.UserJoinLogger;

public class OnUserJoin extends ListenerAdapter {
    private DiscordBot bot = null;
    public OnUserJoin(DiscordBot discordBot) {
        this.bot = discordBot;
    }

    /**
     * This method is called by JDA when a user joins a guild.
     * Prints a message to the console when a user joins a guild.
     */
    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        new UserJoinLogger(bot).logEvent(event, DiscordBot.isProductionEnabled());
    }
}
