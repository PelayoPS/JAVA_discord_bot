package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class StreamOnline implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "streamonline";

    private final Category category = Category.GENERAL;

    private String description = "Returns the streamers that are online";

    // ====================CONSTRUCTOR SECTION====================//

    public StreamOnline() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name streamonline is used this method is called
     * it sends a message to the channel with the users who are streaming and their links
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        List<Member> members = event.getJDA().getGuilds().get(0).getMembers();
        List<String> streamLinks = new ArrayList<>();
        members.stream().filter(member ->
            !member.getUser().isBot() && member.getRoles().contains(event.getGuild().getRolesByName("Streamer", true).get(0)))//user is streamer
                .forEach(member -> {
                    member.getActivities().stream().filter(activity -> activity.getType().equals(net.dv8tion.jda.api.entities.Activity.ActivityType.STREAMING))//user is streaming
                            .forEach(activity -> {
                                        streamLinks.add(member.getEffectiveName() + " is streaming " + activity.getName() + " at " + activity.getUrl());//get link
                                    }
                            );
                }
        );
        String temp = !streamLinks.isEmpty() ? String.join("\n", streamLinks) : "No streamers are online";
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("Streamers Online")
                .setDescription(temp)
                .build();
        event.replyEmbeds(embed).queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
    }

    /**
     * gets the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * gets the name of the command for management
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * gets the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * gets the description of the command
     * @return
     */
    @Override
    public String getHelp(){
        return description;
    }
}
