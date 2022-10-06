package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.List;
import java.util.stream.Collectors;

public class StreamOnline implements CommandInterface {

    private static final String name = "streamonline";

    private final Category category = Category.GENERAL;

    /**
     * When a slash command with the name streamonline is used this method is called
     * it sends a message to the channel with the users who are streaming and their links
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        /*
         * if a user with the role Streamer is streaming it will send a message with their name and link
         */
        List<Member> streaming = event.getGuild()
                .getMembersWithRoles(event.getGuild().getRolesByName("Streamer", true).get(0))//gets the members with the role Streamer
                .stream()//converts the list to a stream
                .filter(member -> member.getActivities().stream()//filters the stream to only include members who are streaming
                        .anyMatch(activity -> activity.getType() == Activity.ActivityType.STREAMING)).collect(Collectors.toList());//collects the stream to a list
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle("Streamers")
                .setDescription(streaming.stream().map(member -> member.getEffectiveName() + " - " + member.getActivities().get(0).getUrl()).collect(Collectors.joining("\n")))
                .build();
        event.replyEmbeds(messageEmbed).queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, "Returns a list of streamers that are online");
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
}
