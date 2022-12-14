package src.util.logging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import src.DiscordBot;

import java.util.Objects;

public class UserJoinLogger implements Logger<GuildMemberJoinEvent>{

    public UserJoinLogger() {
    }

    /**
     * Logs when a user joins to the channel specified in the config file.
     * @param event
     * @param isProduction
     */
    @Override
    public void logEvent(GuildMemberJoinEvent event, boolean isProduction) {
        if (!isProduction) {
            return;
        }
        /*
         * Sends a message in the serverjoinlog channel when a user joins a guild.
         * format: [username] joined the server.
         */
        String channelID = DiscordBot.getConfig().get("SERVERJOINLOGCHANNELID");
        /*
         * creates and embed message with the user's avatar
         * and sends it to the serverjoinlog channel
         */
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("User joined");
        embed.setDescription("[" + event.getUser().getName() + "] joined the server.");
        embed.setImage(event.getUser().getAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        Objects.requireNonNull(event.getGuild().getTextChannelById(channelID)).sendMessageEmbeds(msgEmbed).queue();
        System.out.println("User joined guild!");
    }
}
