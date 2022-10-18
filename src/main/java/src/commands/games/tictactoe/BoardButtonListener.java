package src.commands.games.tictactoe;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import org.jetbrains.annotations.NotNull;
import src.listeners.buttons.ButtonListener;

import java.util.Objects;

public class BoardButtonListener extends ButtonListener {

    public BoardButtonListener() {
    }

    /**
     * This method is called when a button is clicked and is from the board
     * we use colors as a way to identify the board until a better method is implemented
     * @param event the event called
     */
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (Objects.requireNonNull(event.getInteraction().getMessage().getEmbeds().get(0).getColor()).equals(TicTacToe.boardColor)) {
            TicTacToe.getGame()
                    .runTurn(
                            Integer.parseInt(Objects.requireNonNull(event.getButton().getId()).split(",")[0]),//x
                            Integer.parseInt(event.getButton().getId().split(",")[1]),//y
                            event);
        }
    }
}
