package src.commands.games.tictactoe;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

import java.util.Arrays;
import java.util.List;

public class Game {
    private final User player1;
    private final User player2;
    private User currentPlayer;
    private final ButtonStyle player1Style = ButtonStyle.SUCCESS;//green color of button
    private final ButtonStyle player2Style = ButtonStyle.DANGER;//red color of button
    private final Board board;

    /**
     * creates a game with two players and a board
     * @param player1
     * @param player2
     * @param board
     */
    public Game(User player1, User player2, Board board) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = board;
    }

    /**
     * changes the value of the button to the current player's symbol
     * if the button is already clicked, it will not change the value
     * if the game is over, it will not change the value
     * if the button is clicked by the other player, it will not change the value
     * @param x
     * @param y
     * @param event
     */
    public void runTurn(int x, int y, ButtonInteractionEvent event) {
        if(!event.getUser().equals(player1) && !event.getUser().equals(player2)) {
            event.reply("You are not a player in this game").setEphemeral(true).queue();
            return;
        }
        if (board.boardButtons[x][y].getLabel().equals("🟦") && event.getUser().equals(currentPlayer)) {
            event.editButton(board.boardButtons[x][y] = board.boardButtons[x][y]
                    .withLabel(currentPlayer.equals(player1) ? board.getPlayer1Symbol() : board.getPlayer2Symbol())
                    .withStyle(currentPlayer.equals(player1) ? player1Style : player2Style)).queue();
            checkWin();
            changeTurn();
            checkDraw();

            return;
        }
        if(!board.boardButtons[x][y].getLabel().equals("🟦")){
            event.reply("This field is already taken").setEphemeral(true).queue();
            return;
        }
        event.reply("This is not your turn").setEphemeral(true).queue();

    }

    /**
     * changes the current player
     */
    private void changeTurn() {
        if (currentPlayer.equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }

    /**
     * checks if the game is over
     * if the game is over, it will send a message to the channel
     * if the game is over, it will disable all buttons
     */
    private void checkWin() {
        if (checkRows() || checkColumns() || checkDiagonals()) {
            board.endGame("Player " + currentPlayer.getAsTag() + " has won!");
        }
        if(checkDraw()){
            board.endGame("It's a draw!");
        }
    }

    /**
     * checks if the game is a draw
     * @return
     */
    private boolean checkDraw() {
        //converts the board to a 1d array of buttons
        List<Button> boardList = Arrays.asList(board.boardButtons[0]);
        boardList.addAll(Arrays.asList(board.boardButtons[1]));
        boardList.addAll(Arrays.asList(board.boardButtons[2]));
        //checks if all buttons are not blue
        return boardList.stream().noneMatch(button -> button.getLabel().equals("🟦"));
    }

    /**
     * checks if there are 3 of the same symbols in a row
     * @return
     */
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board.boardButtons[i][0].getLabel().equals(board.boardButtons[i][1].getLabel()) &&
                    board.boardButtons[i][1].getLabel().equals(board.boardButtons[i][2].getLabel()) &&
                    !board.boardButtons[i][0].getLabel().equals("🟦")) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if there are 3 of the same symbols in a column
     * @return
     */
    private boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board.boardButtons[0][i].getLabel().equals(board.boardButtons[1][i].getLabel()) &&
                    board.boardButtons[1][i].getLabel().equals(board.boardButtons[2][i].getLabel()) &&
                    !board.boardButtons[0][i].getLabel().equals("🟦")) {
                return true;
            }
        }
        return false;
    }

    /**
     * checks if there are 3 of the same symbols in a diagonal
     * @return
     */
    private boolean checkDiagonals() {
        if (board.boardButtons[0][0].getLabel().equals(board.boardButtons[1][1].getLabel()) &&
                board.boardButtons[1][1].getLabel().equals(board.boardButtons[2][2].getLabel()) &&
                !board.boardButtons[0][0].getLabel().equals("🟦")) {
            return true;
        }
        return board.boardButtons[0][2].getLabel().equals(board.boardButtons[1][1].getLabel()) &&
                board.boardButtons[1][1].getLabel().equals(board.boardButtons[2][0].getLabel()) &&
                !board.boardButtons[0][2].getLabel().equals("🟦");
    }


}
