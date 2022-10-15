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
        if (event.getInteraction().getMessage().getEmbeds().get(0).getColor().equals(HelpMenuCreator.helpColor)) {
            MessageEmbed messageEmbed = new EmbedBuilder()
                    .setAuthor("HELP")
                    .setTitle("Page: " + event.getComponentId())
                    .setDescription(HelpMenuCreator.getMenu(Category.valueOf(event.getComponentId())))
                    .setFooter("Select a category to see the commands")
                    .setColor(HelpMenuCreator.helpColor)
                    .build();
            event.editMessageEmbeds(messageEmbed).queue();
        }
    }


}
