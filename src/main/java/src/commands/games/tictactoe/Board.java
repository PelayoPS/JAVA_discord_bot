package src.commands.games.tictactoe;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.requests.restaction.interactions.ReplyCallbackAction;

import java.util.Arrays;

public class Board {

    private String player1Symbol = "\u2705";//"✅";
    private String player2Symbol = "\u274C";//"❌";
    public Board board;
    private SlashCommandInteractionEvent event;
    public Button[][] boardButtons = new Button[3][3];

    /**
     * Creates a new board
     * @param event The event that triggered the command
     */
    public void drawBoard(SlashCommandInteractionEvent event) {
        board = this;
        this.event = event;
        MessageEmbed embed = new EmbedBuilder()
                .setTitle("TicTacToe")
                .setDescription(
                        "Your game vs " + event.getOption("opponent").getAsMember().getAsMention()+" has just started\n"
                                + "To make a move, click the corresponding button\n"
                )
                .addField("Player 1: ", event.getUser().getAsTag(), true)
                .addField("Player 2: ", event.getOption("opponent").getAsUser().getAsTag(), true)
                .setColor(TicTacToe.boardColor)
                .build();
        ReplyCallbackAction replyCallbackAction = event.replyEmbeds(embed);
        replyCallbackAction = addButtons(replyCallbackAction);
        replyCallbackAction.queue();

    }

    /**
     * Adds the buttons to the message
     * @param replyCallbackAction The ReplyCallbackAction to add the buttons to
     * @return The ReplyCallbackAction with the buttons added
     */
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


    /**
     * returns the symbol that belongs to the player 1
     * @return
     */
    public String getPlayer1Symbol() {
        return player1Symbol;
    }

    /**
     * returns the symbol that belongs to the player 2
     * @return
     */
    public String getPlayer2Symbol() {
        return player2Symbol;
    }

    /**
     * ends the game when called in the game class
     * edits the message to show the winner
     * @param message
     */
    public void endGame (String message) {
        event.getHook().editOriginalEmbeds(new EmbedBuilder()
                .setTitle("TicTacToe")
                .setDescription(message)
                .setColor(TicTacToe.boardColor)
                .build()).queue();
        Arrays.stream(boardButtons).forEach(buttons -> {
            for (Button button : buttons) {
                //disables the button
                button = button.asDisabled();
            }
        });
    }
}


