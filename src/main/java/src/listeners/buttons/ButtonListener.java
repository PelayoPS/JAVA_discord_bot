package src.listeners.buttons;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public abstract class ButtonListener extends ListenerAdapter {

    /**
     * This method is called by JDA when a button is clicked.
     * @param event the event called
     */
    public abstract void onButtonInteraction(@NotNull ButtonInteractionEvent event);

}
