package src.commands.general;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Avatar extends ListenerAdapter {
    /**
     * When a slash command with the name avatar is used this method is called
     * it sends the avatar of the user given to the channel the command was used in
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        try {
            if (event.getName().equals("avatar")) {
                MessageEmbed message = new EmbedBuilder()
                        .setTitle("Avatar of " + event.getOption("user").getAsUser().getName())
                        .setImage(event.getOption("user").getAsUser().getAvatarUrl() + "?format=png&dynamic=true&size=1024")
                        .build();
                event.replyEmbeds(message).queue(); // reply immediately
            }
        } catch (IllegalArgumentException e) {
            event.reply("The user has no avatar").queue();
        }
    }
}
