package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class UserInfo implements CommandInterface {

    private static String name = "userinfo";

    private Category category = Category.GENERAL;

    /**
     * When a slash command with the name userinfo is used this method is called
     * it sends a message to the channel showing the user's info
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String status = getStatus(event.getOption("user"));
        String creationDate = event.getOption("user").getAsUser().getTimeCreated().toString();
        String joinDate = event.getOption("user").getAsMember().getTimeJoined().toString();
        String name = event.getOption("user").getAsUser().getName();
        String id = event.getOption("user").getAsUser().getId();
        String avatar = getAvatar(event);
        MessageEmbed message = new EmbedBuilder()
                .setThumbnail(avatar)
                .setTitle("User info for " + name)
                .addField("Status", status, true)
                .addField("Creation date", creationDate, true)
                .addField("Join date", joinDate, true)
                .addField("Name", name, true)
                .addField("ID", id, true)
                .build();
        event.replyEmbeds(message).queue();
    }

    /**
     * returns the avatar of the user given
     * if the user doesn't have an avatar it returns the default avatar
     * @param event
     * @return
     */
    private String getAvatar(SlashCommandInteractionEvent event){
        try {
            return (event.getOption("user").getAsUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024");
        } catch (IllegalArgumentException e) {
            return (event.getOption("user").getAsUser().getDefaultAvatarUrl() + "?format=png&dynamic=true&size=1024");
        }
    }

    /**
     * This method returns the status of the user
     * Possible statuses are:
     * ONLINE("online"),
     * IDLE("idle"),
     * DO_NOT_DISTURB("dnd"),
     * INVISIBLE("invisible"),
     * OFFLINE("offline"),
     * UNKNOWN("");
     */
    private String getStatus(OptionMapping user) {
        String status = user.getAsMember().getOnlineStatus().getKey();
        switch (status) {
            case "online":
                status = "🟢 Online";
                break;
            case "dnd":
                status = "⛔ No molestar";
                break;
            case "idle":
                status = "🌙 Ausente";
                break;
            case "offline":
                status = "⚪ Desconocido";
                break;
            case "invisible":
                status = "⚫ Invisible";
                break;
        }
        return status;
    }

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash("userinfo", "Returns a full info of the user given")
                .addOption(OptionType.USER, "user", "User to get the info", true);
        return command;
    }

    @Override
    public String getName() {
        return name;
    }

    public static String getNameForManagement() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }
}


