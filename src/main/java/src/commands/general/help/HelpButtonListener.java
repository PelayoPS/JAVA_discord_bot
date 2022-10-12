package src.commands.general.help;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import src.listeners.buttons.ButtonListener;
import src.util.commandPattern.Category;

public class HelpButtonListener extends ButtonListener {

    /**
     * updates the help menu when a button is clicked
     * @param event
     */
    public void onButtonInteraction(ButtonInteractionEvent event) {
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle("HELP - "+ event.getComponentId())
                .setDescription(HelpMenuCreator.getMenu(Category.valueOf(event.getComponentId())))
                .setFooter("Select the category you want to see")
                .build();
        event.editMessageEmbeds(messageEmbed).queue();
    }


}
