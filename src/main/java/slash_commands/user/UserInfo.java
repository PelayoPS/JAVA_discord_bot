package slash_commands.user;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import slash_commands.ISlashCommand;

public class UserInfo implements ISlashCommand {

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessageEmbed messageEmbed = generateEmbed(event);
        event.replyEmbeds(messageEmbed).queue();
    }

    @Override
    public SlashCommandData getSlashInfo() {
        SlashCommandData result = Commands.slash("userinfo",
                "Description: Shows the important info of the specified user")
                .addOption(OptionType.USER, "user", "The user to show info of");
        return result;
    }

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
        setRolesEmbed(member, builder);
        setBadgesEmbed(builder, user);

        return builder.build();
    }

    private void setRolesEmbed(Member member, EmbedBuilder builder) {
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

    private void setBadgesEmbed(EmbedBuilder builder, User user) {
        builder.addField("Badges",
                user.getFlags().toString().replace("[", "")
                        .replace("]", "").replace(",", "\n"),
                true);
    }
}
