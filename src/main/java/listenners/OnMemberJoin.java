package listenners;

import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;

import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class OnMemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        // sends mention welcome!
        
        event.getGuild().getDefaultChannel().asStandardGuildMessageChannel().sendMessage("Welcome " + event.getMember().getAsMention() + " to the server!").queue();
    }
}