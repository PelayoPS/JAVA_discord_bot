package slash_commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

public interface SlashCommand {
    public SlashCommandData getSlashInfo();
    
    public void execute(SlashCommandInteractionEvent event);
}
