package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class Avatar implements CommandInterface {

    // ====================VARIABLES SECTION====================//

    private static final String name = "avatar";
    private static final Category category = Category.GENERAL;
    private final String description = "Returns the avatar of the given user";

    // ====================CONSTRUCTOR SECTION====================//

    public Avatar() {
    }

    // ====================HANDLING SECTION====================//

    /**
     * When a slash command with the name avatar is used this method is called
     * it sends the avatar of the user given to the channel the command was used in
     * if the user doesn't have an avatar it sends a message saying they don't have an avatar
     * with the default avatar
     * @param event the event called
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        try {
            //when the avatar exists
            event.replyEmbeds(getEmbedWithAvatar(event)).queue();
        } catch (IllegalArgumentException e) {
            //when the avatar doesn't exist
            event.replyEmbeds(getEmbedWithDefaultAvatar(event)).queue();
        }
    }

    /**
     * returns the avatar when there is one
     * @param event the event with the information
     */
    private MessageEmbed getEmbedWithAvatar(SlashCommandInteractionEvent event){
        return new EmbedBuilder()
                .setTitle("Avatar of " + Objects.requireNonNull(event.getOption("user")).getAsUser().getName())
                .setImage(Objects.requireNonNull(event.getOption("user")).getAsUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .build();
    }

    /**
     * @return the default avatar when there isn't one
     */
    private MessageEmbed getEmbedWithDefaultAvatar(SlashCommandInteractionEvent event){
        return new EmbedBuilder()
                .setTitle(Objects.requireNonNull(event.getOption("user")).getAsUser().getName() + " does not have an avatar")
                .setImage(Objects.requireNonNull(event.getOption("user")).getAsUser().getDefaultAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .build();
    }

    // ====================RETURN INFO SECTION====================//

    /**
     * @return the command data for the command
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "user", "The user to get the avatar of", true);
    }

    /**
     * @return the name of the command for the command manager
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * @return the name of the command for general use
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * @return the category of the command
     */
    @Override
    public Category getCategory(){
        return category;
    }

    /**
     * @return the description of the command
     */
    @Override
    public String getHelp(){
        return description;
    }
}

