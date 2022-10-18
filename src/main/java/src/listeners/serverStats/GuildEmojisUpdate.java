package src.listeners.serverStats;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.emoji.EmojiAddedEvent;
import net.dv8tion.jda.api.events.emoji.EmojiRemovedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;

import java.util.Objects;

public class GuildEmojisUpdate extends ListenerAdapter {

    private static final Dotenv env = DiscordBot.getConfig();
    private static final String channel = env.get("EMOJICOUNTCHANNELID");

    /**
     * updates the emoji counter when called
     * @param guild
     */
    public static void updateStats(Guild guild) {
        Objects.requireNonNull(guild.getVoiceChannelById(channel)).getManager().setName("Emojis: " + guild.getEmojis().size()).queue();
    }

    /**
     * This method is called by JDA when an emoji is added to a guild.
     * @param event
     */
    @Override
    public void onEmojiAdded(EmojiAddedEvent event) {
        System.out.println("Emoji added: " + event.getEmoji().getName());
        Objects.requireNonNull(event.getGuild().getVoiceChannelById(channel)).getManager().setName("Emojis: " + event.getGuild().getEmojis().size()).queue();
    }

    /**
     * This method is called by JDA when an emoji is removed from a guild.
     * @param event
     */
    @Override
    public void onEmojiRemoved(EmojiRemovedEvent event) {
        System.out.println("Emoji removed: " + event.getEmoji().getName());
        Objects.requireNonNull(event.getGuild().getVoiceChannelById(channel)).getManager().setName("Emojis: " + event.getGuild().getEmojis().size()).queue();
    }
}
