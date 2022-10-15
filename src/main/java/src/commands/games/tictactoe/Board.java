package src.commands.games.tictactoe;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

import java.awt.*;

public class Board {

    private static SlashCommandInteractionEvent event;
    private String player1;
    private String player2;

    public static Board board;
    public static final Color boardColor = Color.GREEN;

    public static Button[][] boardButtons = new Button[3][3];


    public Board(SlashCommandInteractionEvent event) {
        this.event = event;
        this.player1 = event.getUser().getAsTag();
        this.player2 = event.getOption("opponent").getAsUser().getAsTag();
        board = this;
    }

    public void drawBoard() {
        ReplyCallbackAction replyCallbackAction = this.event.replyEmbeds(
                new EmbedBuilder()
                        .setTitle("TicTacToe")
                        .setDescription(
                           "Your game vs" + event.getOption("opponent").getAsUser().getAsMention() +"has just started\n"
                            + "To make a move, click the corresponding button\n"
                        )
                        .setColor(boardColor)
                        .build()
        );
        replyCallbackAction = addButtons(replyCallbackAction);
        replyCallbackAction.queue();
    }

    private ReplyCallbackAction addButtons(ReplyCallbackAction replyCallbackAction) {
        boardButtons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = Button.of(ButtonStyle.PRIMARY, i+","+j, "🟦");
            }
        }
        return replyCallbackAction
                .addActionRow(
                        boardButtons[0]
                ).addActionRow(
                        boardButtons[1]
                ).addActionRow(
                        boardButtons[2]
                );
    }

    public static SlashCommandInteractionEvent getEvent() {
        return event;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }
}


