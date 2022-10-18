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

import java.time.OffsetDateTime;
import java.util.Objects;

public class UserInfo implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "userinfo";

    private final Category category = Category.GENERAL;

    private final String description = "Returns information about a user";

    // ====================CONSTRUCTOR SECTION====================//

    public UserInfo() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name userinfo is used this method is called
     * it sends a message to the channel showing the user's info
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String status = getStatus(Objects.requireNonNull(event.getOption("user")));
        OffsetDateTime creationDate = Objects.requireNonNull(event.getOption("user")).getAsUser().getTimeCreated();
        //formats the creation date
        String creationDateS = creationDate.getYear() + "-" + creationDate.getMonthValue() + "-" + creationDate.getDayOfMonth();
        OffsetDateTime joinDate = Objects.requireNonNull(Objects.requireNonNull(event.getOption("user")).getAsMember()).getTimeJoined();
        //formats the join date
        String joinDateS = joinDate.getYear() + "-" + joinDate.getMonthValue() + "-" + joinDate.getDayOfMonth();
        String name = Objects.requireNonNull(event.getOption("user")).getAsUser().getName();
        String id = Objects.requireNonNull(event.getOption("user")).getAsUser().getId();
        String avatar = getAvatar(event);
        MessageEmbed message = new EmbedBuilder()
                .setThumbnail(avatar)
                .setTitle("User info for " + name)
                .addField("Status", status, true)
                .addField("Creation date", creationDateS, true)
                .addField("Join date", joinDateS, true)
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
            return (Objects.requireNonNull(event.getOption("user")).getAsUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024");
        } catch (IllegalArgumentException e) {
            return (Objects.requireNonNull(event.getOption("user")).getAsUser().getDefaultAvatarUrl() + "?format=png&dynamic=true&size=1024");
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
        String status = Objects.requireNonNull(user.getAsMember()).getOnlineStatus().getKey();
        status = switch (status) {
            case "online" -> "🟢 Online";
            case "dnd" -> "⛔ No molestar";
            case "idle" -> "🌙 Ausente";
            case "offline" -> "⚪ Desconocido";
            case "invisible" -> "⚫ Invisible";
            default -> Objects.requireNonNull(user.getAsMember()).getOnlineStatus().getKey();
        };
        return status;
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "user", "User to get the info", true);
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
     * gets the category of the command
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
    public String getHelp() {
        return description;
    }
}


