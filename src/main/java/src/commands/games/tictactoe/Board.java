package src.commands.games.tictactoe;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

public class Board {

    private static SlashCommandInteractionEvent event;
    private ButtonStyle player1Style = ButtonStyle.SUCCESS;
    private ButtonStyle player2Style = ButtonStyle.DANGER;


    public Board(SlashCommandInteractionEvent event) {
        this.event = event;
    }

    public ReplyCallbackAction drawBoard() {
        ReplyCallbackAction replyCallbackAction = this.event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("TicTacToe")
                        .setDescription(
                           "Your game vs" + event.getOption("opponent").getAsUser().getAsMention() +"has just started\n"
                            + "To make a move, click the corresponding button\n"
                        )
                        .build()
        );
        ReplyCallbackAction boardReplyCallBackAction = addButtons(replyCallbackAction);
        return boardReplyCallBackAction;
    }

    private ReplyCallbackAction addButtons(ReplyCallbackAction replyCallbackAction) {
        return replyCallbackAction
                .addActionRow(
                        Button.of(ButtonStyle.PRIMARY, "1", "1"),
                        Button.of(ButtonStyle.PRIMARY, "2", "2"),
                        Button.of(ButtonStyle.PRIMARY, "3", "3")
                )
                .addActionRow(
                        Button.of(ButtonStyle.PRIMARY, "4", "4"),
                        Button.of(ButtonStyle.PRIMARY, "5", "5"),
                        Button.of(ButtonStyle.PRIMARY, "6", "6")
                )
                .addActionRow(
                        Button.of(ButtonStyle.PRIMARY, "7", "7"),
                        Button.of(ButtonStyle.PRIMARY, "8", "8"),
                        Button.of(ButtonStyle.PRIMARY, "9", "9")
                );
    }

    public static SlashCommandInteractionEvent getEvent() {
        return event;
    }
}


