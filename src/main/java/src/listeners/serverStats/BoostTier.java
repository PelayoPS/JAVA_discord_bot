package src.listeners.serverStats;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.guild.update.GuildUpdateBoostTierEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;

import java.util.Objects;

public class BoostTier extends ListenerAdapter {

    static final Dotenv env = DiscordBot.getConfig();

    /**
     * This method is called by JDA when a guild's boost tier is updated.
     * @param event the event called
     */
    @Override
    public void onGuildUpdateBoostTier(GuildUpdateBoostTierEvent event) {
        Objects.requireNonNull(event.getGuild().getVoiceChannelById(env.get("BOOSTTIERCHANNELID"))).getManager().setName("Boosts: " + event.getGuild().getBoostTier().getKey()).queue();
    }

    public static void updateStats(Guild guild) {
        Objects.requireNonNull(guild.getVoiceChannelById(env.get("BOOSTTIERCHANNELID"))).getManager().setName("Boosts: " + guild.getBoostTier().getKey()).queue();
    }
}
