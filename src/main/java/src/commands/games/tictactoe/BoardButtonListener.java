package src.commands.games.tictactoe;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import src.listeners.buttons.ButtonListener;

public class BoardButtonListener extends ButtonListener {

    private ButtonGameManager buttonGameManager;

    public BoardButtonListener() {
        this.buttonGameManager = new ButtonGameManager();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getInteraction().getMessage().getEmbeds().get(0).getColor().equals(Board.boardColor)) {
            this.buttonGameManager.runTurn(event);
        }
    }
}
