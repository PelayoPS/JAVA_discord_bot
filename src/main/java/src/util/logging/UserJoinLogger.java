package src.util.logging;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import src.DiscordBot;

public class UserJoinLogger implements Logger<GuildMemberJoinEvent>{

    private DiscordBot bot;

    public UserJoinLogger(DiscordBot bot) {
        this.bot = bot;
    }

    @Override
    public void logEvent(GuildMemberJoinEvent event) {
        /*
         * Sends a message in the serverjoinlog channel when a user joins a guild.
         * format: [username] joined the server.
         */
        String channelID = bot.getConfig().get("SERVERJOINLOGCHANNELID");
        /*
         * creates and embed message with the user's avatar
         * and sends it to the serverjoinlog channel
         */
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("User joined");
        embed.setDescription("[" + event.getUser().getName() + "] joined the server.");
        embed.setImage(event.getUser().getAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        event.getGuild().getTextChannelById(channelID).sendMessageEmbeds(msgEmbed).queue();
        System.out.println("User joined guild!");
    }
}
