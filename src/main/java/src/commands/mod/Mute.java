package src.commands.mod;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.DiscordBot;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.List;
import java.util.Objects;

public class Mute implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "mute";

    private final Category category = Category.MOD;

    private final String description = "Mutes the user given";

    final Dotenv config = DiscordBot.getConfig();

    // ====================CONSTRUCTOR SECTION====================//

    public Mute() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name mute is used this method is called
     * it mutes the user given for the amount of time given
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.ADMINISTRATOR)){
            return;
        }
        try {
            muteUser(Objects.requireNonNull(event.getOption("user")), Objects.requireNonNull(event.getOption("time")));
            logMute(event, Objects.requireNonNull(event.getOption("user")), Objects.requireNonNull(event.getOption("time")));
        } catch (HierarchyException e) {
            event.reply("I can't mute a user with higher or equal role than me").queue();
        }
    }

    /**
     * private method to make the code cleaner
     * @param user
     * @param time
     */
    private void muteUser(OptionMapping user, OptionMapping time) throws HierarchyException {
        /*
         * sends a message to the user saying they have been muted
         * gives the user the muted role
         * removes the muted role after the time given
         */
        user.getAsUser().openPrivateChannel().queue((channel) -> channel.sendMessage("You have been muted for " + time.getAsLong() + " seconds").queue());
        //muted rol
        Role role = Objects.requireNonNull(user.getAsMember()).getGuild().getRolesByName("muted",true).get(0);
        //adds muted role
        user.getAsMember().getGuild()
            .addRoleToMember(user.getAsMember(), role).queue();
        List<Role> roles = user.getAsMember().getRoles();
        user.getAsMember().getRoles().forEach((r) -> {
            //removes all other roles
            user.getAsMember().getGuild()
                .removeRoleFromMember(user.getAsMember(), r).queue();
        });
        //removes muted role after time given
        user.getAsMember().getGuild()
            .removeRoleFromMember(user.getAsMember(), role)
                .queueAfter(time.getAsLong(), java.util.concurrent.TimeUnit.SECONDS);
        roles.forEach((r) -> {
            //removes all other roles
            user.getAsMember().getGuild()
                    .addRoleToMember(user.getAsMember(), r).queueAfter(time.getAsLong(), java.util.concurrent.TimeUnit.SECONDS);
        });
    }

    /**
     * private method to log when a user is muted
     * @param event
     * @param user
     * @param time
     */
    private void logMute(SlashCommandInteractionEvent event, OptionMapping user, OptionMapping time) {
        /*
         * sends a message to the log channel saying who muted who for how long
         */
        Objects.requireNonNull(Objects.requireNonNull(event.getGuild()).getTextChannelById(config.get("MUTEDLOGCHANNELID")))
            .sendMessage(user.getAsUser().getAsTag() +
                    " was muted by " + event.getUser().getAsTag() +
                    " for " + time.getAsLong() + " seconds with reason: " + Objects.requireNonNull(event.getOption("reason")).getAsString()).queue();
        event.reply("Muted " + user.getAsUser().getAsTag() + " for " + time.getAsLong() + " seconds").queue();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * @return the name of the command
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "user", "The user to mute", true)
                .addOption(OptionType.STRING, "reason", "The reason for the mute", true)
                .addOption(OptionType.INTEGER, "time", "The time to mute the user for", true);
    }

    /**
     * @return the name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @return the category of the command
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * @return the name of the command for management
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * @return the description of the command
     */
    @Override
    public String getHelp() {
        return description;
    }
}
