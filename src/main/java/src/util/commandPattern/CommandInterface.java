package src.util.commandPattern;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public interface CommandInterface {
    void handle(SlashCommandInteractionEvent event);
    CommandData getSlash();

    String getName();

    Category getCategory();

}
