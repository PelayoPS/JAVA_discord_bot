package src.commands.games.tictactoe;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.awt.*;

public class TicTacToe implements CommandInterface {

    private static String name = "tictactoe";
    private String description = "play a game of tictactoe";
    private Category category = Category.GAME;
    private Board board;
    private static Game game;
    public static Color boardColor = Color.GREEN;

    /**
     * Starts the board
     * Draws the board
     * Starts the game
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        this.board = new Board();
        this.board.drawBoard(event);
        this.game = new Game(event.getUser(), event.getOption("opponent").getAsUser(), board);
    }

    /**
     * Creates the commandData
     * @return The commandData
     */
    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description)
                .addOption(OptionType.USER, "opponent", "the user you want to play against", true);
        return commandData;
    }

    /**
     * Gets the name of the command
     * @return The name of the command
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Gets the category of the command
     * @return The category of the command
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the description of the command
     * @return the description of the command
     */
    @Override
    public String getHelp() {
        return description;
    }

    /**
     * Gets the name for management
     * @return The name for management
     */
    public static String getNameForManagement() {
        return name;
    }

    /**
     * Gets the game
     * @return The game
     */
    public static Game getGame() {
        return game;
    }
}
