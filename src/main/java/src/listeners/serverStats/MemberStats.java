package src.listeners.serverStats;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Guild;
import src.DiscordBot;

import java.util.Objects;


public class MemberStats {
    private static final Dotenv env = DiscordBot.getConfig();

    /**
     * updates the member counter when called
     * @param guild
     */
    public static void updateStats(Guild guild) {
        String allMemberChannel = env.get("ALLMEMBERSCOUNTCHANNELID");
        String memberChannel = env.get("MEMBERSCOUNTCHANNELID");
        String botChannel = env.get("BOTSCOUNTCHANNELID");

        Objects.requireNonNull(guild.getVoiceChannelById(allMemberChannel)).getManager().setName("All Members: " + guild.getMemberCount()).queue();
        Objects.requireNonNull(guild.getVoiceChannelById(memberChannel)).getManager().setName("Members: " + guild.getMembers().stream().filter(member -> !member.getUser().isBot()).count()).queue();
        Objects.requireNonNull(guild.getVoiceChannelById(botChannel)).getManager().setName("Bots: " + guild.getMembers().stream().filter(member -> member.getUser().isBot()).count()).queue();
    }
}
