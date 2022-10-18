package src.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import src.DiscordBot;
import src.listeners.serverStats.MemberStats;
import src.util.logging.UserJoinLogger;

import java.util.Objects;

public class OnUserJoin extends ListenerAdapter {
    public OnUserJoin() {
    }

    /**
     * This method is called by JDA when a user joins a guild.
     * Prints a message to the console when a user joins a guild.
     */
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        new UserJoinLogger().logEvent(event, DiscordBot.isProductionEnabled());
        String channelString = DiscordBot.getConfig().get("WELCOMECHANNELID");
        Objects.requireNonNull(event.getGuild().getTextChannelById(channelString))
                .sendMessageEmbeds(getWelcomeMessage(event)).queue();
        MemberStats.updateStats(event.getGuild());
    }

    private MessageEmbed getWelcomeMessage(GuildMemberJoinEvent event) {
        return new EmbedBuilder()
                .setTitle("Welcome to the server!")
                .setDescription("Welcome to the server! Please read the rules and enjoy your stay!")
                .setImage(event.getUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .addField("User", event.getUser().getAsMention(), true)
                .build();
    }
}
