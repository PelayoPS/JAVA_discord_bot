package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class Avatar implements CommandInterface {
    private static String name = "avatar";

    private static Category category = Category.GENERAL;

    /**
     * When a slash command with the name avatar is used this method is called
     * it sends the avatar of the user given to the channel the command was used in
     * if the user doesn't have an avatar it sends a message saying they don't have an avatar
     * with the default avatar
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        try {
            //when the avatar exists
            event.replyEmbeds(getEmbedWithAvatar(event)).queue(); // reply immediately
        } catch (IllegalArgumentException e) {
            //when the avatar doesn't exist
            event.replyEmbeds(getEmbedWithDefaultAvatar(event)).queue(); // reply immediately
        }
    }

    /**
     * returns the avatar when there is one
     * @param event
     */
    private MessageEmbed getEmbedWithAvatar(SlashCommandInteractionEvent event){
        MessageEmbed message = new EmbedBuilder()
                .setTitle("Avatar of " + event.getOption("user").getAsUser().getName())
                .setImage(event.getOption("user").getAsUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .build();
        return message;
    }

    /**
     * returns the default avatar when there isn't one
     * @return
     */
    private MessageEmbed getEmbedWithDefaultAvatar(SlashCommandInteractionEvent event){
        MessageEmbed message = new EmbedBuilder()
                .setTitle(event.getOption("user").getAsUser().getName() + " does not have an avatar")
                .setImage(event.getOption("user").getAsUser().getDefaultAvatarUrl() + "?format=png&dynamic=true&size=1024")
                .build();
        return message;
    }

    /**
     * Returns the command data for the command
     * @return
     */
    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Sends the avatar of the user given")
                .addOption(OptionType.USER, "user", "The user to get the avatar of", true);
        return command;
    }

    /**
     * Returns the name of the command for the command manager
     * @return
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * Returns the name of the command for general use
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the category of the command
     * @return
     */
    public Category getCategory(){
        return category;
    }
}

