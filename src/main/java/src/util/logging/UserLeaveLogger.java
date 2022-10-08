package src.util.logging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import src.DiscordBot;

public class UserLeaveLogger implements Logger<GuildMemberRemoveEvent> {

    private final DiscordBot bot;

    public UserLeaveLogger(DiscordBot bot) {
        this.bot = bot;
    }

    /**
     * Logs when a user leaves to the channel specified in the config file.
     * @param event
     * @param isProduction
     */
    @Override
    public void logEvent(GuildMemberRemoveEvent event, boolean isProduction) {
        if (!isProduction) {
            return;
        }
        /*
         * Sends a message in the serverleavelog channel when a user leaves a guild.
         * format: [username] left the server.
         */
        String channelID = DiscordBot.getConfig().get("SERVERLEAVELOGCHANNELID");
        /*
         * creates and embed message with the user's avatar
         * and sends it to the serverleavelog channel
         */
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("User left");
        embed.setDescription("[" + event.getUser().getName() + "] left the server.");
        embed.setImage(event.getUser().getAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        event.getGuild().getTextChannelById(channelID).sendMessageEmbeds(msgEmbed).queue();
        System.out.println(event.getUser().getName() + "User left guild!");
    }
}
