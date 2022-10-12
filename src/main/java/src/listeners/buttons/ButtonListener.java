package src.listeners.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public abstract class ButtonListener extends ListenerAdapter {

    /**
     * This method is called by JDA when a button is clicked.
     * @param event
     */
    public abstract void onButtonInteraction(ButtonInteractionEvent event);

}
