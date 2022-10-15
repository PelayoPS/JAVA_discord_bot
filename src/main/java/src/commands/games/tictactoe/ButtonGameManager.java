package src.commands.games.tictactoe;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class ButtonGameManager {

    private SlashCommandInteractionEvent slashEvent;
    private ButtonStyle player1Style = ButtonStyle.SUCCESS;
    private ButtonStyle player2Style = ButtonStyle.DANGER;

    public ButtonGameManager() {
         this.slashEvent = Board.getEvent();
    }

    public void runTurn(ButtonInteractionEvent event) {
        MessageEmbed messageEmbed = new EmbedBuilder()
                .setTitle("TicTacToe")
                .setDescription(
                        "Your game vs" + event.getGuild().getMemberByTag(Board.board.getPlayer2()) +"has just started\n"
                                + "To make a move, click the corresponding button\n"
                )
                .setColor(Board.boardColor)
                .build();
        String buttonID = event.getButton().getId();
        Button[][] board = addButtons(buttonID, event);
        board = Board.boardButtons;
        String[] buttonIDSplit = buttonID.split(",");
        //edits the embed with 3 rows of buttons
        event.editMessageEmbeds(messageEmbed).setComponents(
                ActionRow.of(board[0]),
                ActionRow.of(board[1]),
                ActionRow.of(board[2])
        ).queue();

    }

    private Button[][] addButtons(String buttonID, ButtonInteractionEvent event) {
        String[] buttonIDSplit = buttonID.split(",");
        Board.boardButtons[Integer.parseInt(buttonIDSplit[0])][Integer.parseInt(buttonIDSplit[1])] = Button.of(
                event.getUser().getAsTag().equals(Board.board.getPlayer1()) ? player1Style : player2Style,
                buttonID,
                //green checkmark emoji
                event.getUser().getAsTag().equals(Board.board.getPlayer1()) ? "\u2705" : "\u274C"
        );
        Button[][] board = Board.boardButtons;
        return board;
    }
}
