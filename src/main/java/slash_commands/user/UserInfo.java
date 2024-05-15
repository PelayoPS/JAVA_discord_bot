package slash_commands.user;

import java.util.List;
import java.util.Objects;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.Code;
import slash_commands.ISlashCommand;

/**
 * The UserInfo class implements the ISlashCommand interface and represents a
 * slash command
 * that shows important information about a specified user.
 */
public class UserInfo implements ISlashCommand {

    private final Code code = Code.USER;

    /* =========================SUPER METHODS=============================== */

    /**
     * Executes the slash command.
     * This method is called when the slash command is triggered by a user.
     *
     * @param event The SlashCommandInteractionEvent representing the interaction
     *              event.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessageEmbed messageEmbed = generateEmbed(event);
        event.replyEmbeds(messageEmbed).queue();
    }

    /**
     * Retrieves the slash command information.
     * This method is called to get the SlashCommandData object that represents the
     * command's data.
     *
     * @return The SlashCommandData object representing the command's data.
     */
    @Override
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("userinfo",
                "Description: Shows the important info of the specified user")
                .addOption(OptionType.USER, "user", "The user to show info of");
        return result;
    }

    /**
     * Retrieves the help information for the slash command.
     *
     * @return A string containing information about how to use the command.
     */
    @Override
    public String getHelp() {
        return "Usage: /userinfo [user]\n" + "Description: Shows the important info of the specified user";
    }

    /**
     * Retrieves the command code.
     *
     * @return The command code.
     */
    @Override
    public Code getCode() {
        return code;
    }

    /* ========================PRIVATE METHODS=============================== */

    /**
     * Generates an embed message containing the user information.
     *
     * @param event The SlashCommandInteractionEvent representing the interaction
     *              event.
     * @return The MessageEmbed object containing the user information.
     */
    private MessageEmbed generateEmbed(SlashCommandInteractionEvent event) {
        User user = event.getOption("user") == null
                ? event.getUser()
                : event.getOption("user").getAsUser();
        EmbedBuilder builder = new EmbedBuilder();
        Guild guild = event.getGuild();
        builder.setTitle("User Information");
        builder.setColor(0x0000FF);
        builder.setThumbnail(user.getAvatarUrl());
        builder.addField("Username", user.getName(), true);
        builder.addField("ID", user.getId(), true);

        Member member = guild.getMemberById(user.getId());
        setServerInfoEmbed(member, builder);
        setBadgesEmbed(builder, user);
        String banner = getBanner(user);
        if (banner != null) {
            builder.setImage(banner);
        }

        return builder.build();
    }

    /**
     * Creates the needed fields for all the server info
     * - Join date
     * - Roles
     * 
     * @param member  member to get the information from
     * @param builder builder that creates the embed message
     */
    private void setServerInfoEmbed(Member member, EmbedBuilder builder) {
        if (member != null) {
            // roles
            StringBuilder roles = new StringBuilder();
            member.getRoles().stream().forEach(
                    role -> roles.append(role.getAsMention()).append(", "));
            builder.addField("Roles", roles.toString(), true);
            // join time
            builder.addField("Joined At",
                    "<t:" + (member.getTimeJoined().toEpochSecond()) + ":R>"
                            + "/ <t:" + (member.getTimeJoined().toEpochSecond()) + ":d>",
                    true);
            // creation time
            builder.addField("Created At",
                    "<t:" + (member.getUser().getTimeCreated().toEpochSecond()) + ":R>"
                            + "/ <t:" + (member.getUser().getTimeCreated().toEpochSecond()) + ":d>",
                    true);
        } else {
            builder.addField("Roles", "No roles", true);
        }
    }

    /**
     * Shows the badges
     * 
     * @param builder builder that creates the embed message
     * @param user    user that has the badges
     */
    private void setBadgesEmbed(EmbedBuilder builder, User user) {
        builder.addField("Badges",
                user.getFlags().toString(),
                true);
    }

    /**
     * Retrieves the banner of the user.
     * 
     * @param user The user to get the banner from.
     * @return The URL of the user's banner.
     */
    private String getBanner(User user) {
        String url = user.retrieveProfile()
                .map(profile -> profile.getBannerUrl())
                .complete();
        if (url != null) {
            System.out.println("User banner URL: " + url);
        } else {
            return "User does not have a banner";
        }
        return url + "?dynamic=true&size=1024";
    }
    

}
