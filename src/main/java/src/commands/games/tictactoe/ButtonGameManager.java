package src.commands.games.tictactoe;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public class ButtonGameManager {

    private SlashCommandInteractionEvent slashEvent;

    public ButtonGameManager() {
         this.slashEvent = Board.getEvent();
    }

    public void runTurn(ButtonInteractionEvent event) {
        if (event.getUser().getId().equals(slashEvent.getUser().getId())) {
            //gets the original message and edits it
            //with the new button style
            ReplyCallbackAction replyCallbackAction = new Board(slashEvent).drawBoard();
            MessageEmbed embed = replyCallbackAction.getEmbeds().get(0);
            replyCallbackAction = event.replyEmbeds(embed);
            replyCallbackAction = addButtons(replyCallbackAction, event);

        }
    }

    private ReplyCallbackAction addButtons(ReplyCallbackAction replyCallbackAction, ButtonInteractionEvent event) {
        Button[][] board = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = Button.of(ButtonStyle.PRIMARY, i+"", i+"");
            }
        }
        ButtonStyle buttonStyle = ButtonStyle.PRIMARY;
        if (event.getUser().getId().equals(slashEvent.getUser().getId())) {
            buttonStyle = ButtonStyle.SUCCESS;
        }
        if (event.getUser().getId().equals(slashEvent.getOption("opponent").getAsUser().getId())) {
            buttonStyle = ButtonStyle.DANGER;
        }
        board[Integer.parseInt(event.getComponentId())][Integer.parseInt(event.getComponentId())] =
                Button.of(buttonStyle, event.getComponentId(), event.getComponentId());
        replyCallbackAction
                .addActionRow(
                        board[0][0],
                        board[0][1],
                        board[0][2]
                )
                .addActionRow(
                        board[1][0],
                        board[1][1],
                        board[1][2]
                )
                .addActionRow(
                        board[2][0],
                        board[2][1],
                        board[2][2]
                );
        return replyCallbackAction;
    }
}
