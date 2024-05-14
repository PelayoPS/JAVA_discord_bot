package slash_commands.user;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.ISlashCommand;

/**
 * The UserInfo class implements the ISlashCommand interface and represents a
 * slash command
 * that shows important information about a specified user.
 */
public class UserInfo implements ISlashCommand {

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
        builder.addField("Created At",
                "<t:" + (user.getTimeCreated().toEpochSecond()) + ":R>"
                        + "/ <t:" + (user.getTimeCreated().toEpochSecond()) + ":d>",
                true);

        Member member = guild.getMemberById(user.getId());
        setServerInfoEmbed(member, builder);
        setBadgesEmbed(builder, user);

        return builder.build();
    }

    /**
     * Creates the needed fields for all the server info
     * - Join date
     * - Roles
     * 
     * @param member member to get the information from
     * @param builder builder that creates the embed message
     */
    private void setServerInfoEmbed(Member member, EmbedBuilder builder) {
        if (member != null) {
            builder.addField("Joined At",
                    "<t:" + (member.getTimeJoined().toEpochSecond()) + ":R>"
                            + "/ <t:" + (member.getTimeJoined().toEpochSecond()) + ":d>",
                    true);
            StringBuilder roles = new StringBuilder();
            member.getRoles().stream().forEach(
                    role -> roles.append(role.getAsMention()).append(", "));
            builder.addField("Roles", roles.toString(), true);
        } else {
            builder.addField("Roles", "No roles", true);
        }
    }

    /**
     * Shows the badges 
     * 
     * @param builder builder that creates the embed message
     * @param user user that has the badges
     */
    private void setBadgesEmbed(EmbedBuilder builder, User user) {
        builder.addField("Badges",
                user.getFlags().toString(),
                true);
    }
}
