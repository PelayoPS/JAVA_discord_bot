package src.commands.games.tictactoe;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.awt.*;
import java.util.Objects;

public class TicTacToe implements CommandInterface {

    private static final String name = "tictactoe";
    private final String description = "play a game of tictactoe";
    private final Category category = Category.GAME;
    private static Game game;
    public static final Color boardColor = Color.GREEN;

    /**
     * Starts the board
     * Draws the board
     * Starts the game
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        Board board = new Board();
        board.drawBoard(event);
        game = new Game(event.getUser(), Objects.requireNonNull(event.getOption("opponent")).getAsUser(), board);
    }

    /**
     * Creates the commandData
     * @return The commandData
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description)
                .addOption(OptionType.USER, "opponent", "the user you want to play against", true);
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
