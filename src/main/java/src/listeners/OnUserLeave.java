package src.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;

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
        /*
         * Sends a message in the serverleavelog channel when a user leaves a guild.
         * format: [username] left the server.
         */
        String channelID = bot.getConfig().get("SERVERLEAVELOGCHANNELID");
        /*
         * creates and embed message with the user's avatar
         * and sends it to the serverleavelog channel
         */
        System.out.println(event.getUser().getName());
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("User left");
        embed.setDescription("[" + event.getUser().getName() + "] left the server.");
        embed.setImage(event.getUser().getAvatarUrl());
        MessageEmbed msgEmbed = embed.build();
        event.getGuild().getTextChannelById(channelID).sendMessageEmbeds(msgEmbed).queue();
        System.out.println("User left guild!");
    }
}
