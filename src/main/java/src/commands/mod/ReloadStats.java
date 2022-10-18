package src.commands.mod;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.listeners.serverStats.BoostTier;
import src.listeners.serverStats.GuildEmojisUpdate;
import src.listeners.serverStats.MemberStats;
import src.util.commandPattern.Category;
import src.util.commandPattern.CommandInterface;

import java.util.Objects;

public class ReloadStats implements CommandInterface {

    private static final String name= "reloadstats";
    private final String description = "Reloads the stats for the server.";
    private final Category category = Category.MOD;

    /**
     * When a slash command with the name reloadstats is used this method is called
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        GuildEmojisUpdate.updateStats(Objects.requireNonNull(event.getGuild()));
        MemberStats.updateStats(event.getGuild());
        BoostTier.updateStats(event.getGuild());
        event.reply("Stats reloaded").queue();
    }

    /**
     * returns the command data
     * @return
     */
    @Override
    public CommandData getSlash() {
        return Commands.slash(name, description);
    }

    /**
     * returns the name of the command
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * returns the category of the command
     * @return
     */
    @Override
    public Category getCategory() {
        return category;
    }

    /**
     * returns the description of the command
     * @return
     */
    @Override
    public String getHelp() {
        return description;
    }

    /**
     * returns the name of the command
     * @return
     */
    public static String getNameForManagement(){
        return name;
    }
}
