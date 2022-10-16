package src.listeners.serverStats;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.entities.Guild;
import src.DiscordBot;


public class MemberStats {
    private static Dotenv env = DiscordBot.getConfig();

    /**
     * updates the member counter when called
     * @param guild
     */
    public static void updateStats(Guild guild) {
        String allMemberChannel = env.get("ALLMEMBERSCOUNTCHANNELID");
        String memberChannel = env.get("MEMBERSCOUNTCHANNELID");
        String botChannel = env.get("BOTSCOUNTCHANNELID");

        guild.getVoiceChannelById(allMemberChannel).getManager().setName("All Members: " + guild.getMemberCount()).queue();
        guild.getVoiceChannelById(memberChannel).getManager().setName("Members: " + guild.getMembers().stream().filter(member -> !member.getUser().isBot()).count()).queue();
        guild.getVoiceChannelById(botChannel).getManager().setName("Bots: " + guild.getMembers().stream().filter(member -> member.getUser().isBot()).count()).queue();
    }
}
