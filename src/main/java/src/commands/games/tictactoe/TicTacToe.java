package src.commands.games.tictactoe;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

public class TicTacToe implements CommandInterface {

    private static String name = "tictactoe";
    private String description = "play a game of tictactoe";
    private Category category = Category.GAME;

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        new Board(event).drawBoard().queue();
    }

    @Override
    public CommandData getSlash() {
        CommandData commandData = Commands.slash(name, description)
                .addOption(OptionType.USER, "opponent", "the user you want to play against", true);
        return commandData;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    @Override
    public String getHelp() {
        return description;
    }

    public static String getNameForManagement() {
        return name;
    }
}
