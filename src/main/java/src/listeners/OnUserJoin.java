package src.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
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
        String channelString = DiscordBot.getConfig().get("SERVERJOINCHANNELID");
        event.getGuild().getTextChannelById(channelString)
                .sendMessageEmbeds(getWelcomeMessage(event)).queue();
        event.getGuild().getTextChannelById(channelString).sendMessageEmbeds(getWelcomeMessage(event)).queue();
    }

    private MessageEmbed getWelcomeMessage(GuildMemberJoinEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Welcome to the server!")
                .setDescription("Welcome to the server! Please read the rules and enjoy your stay!")
                .setAuthor(event.getUser().getAsMention(), null, event.getUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .build();
        return embed;
    }
}
