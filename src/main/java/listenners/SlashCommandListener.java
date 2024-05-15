package listenners;

import main.CommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import util.logs.SlashCommandLogger;

public class SlashCommandListener extends ListenerAdapter {
    private CommandManager commandManager;

    public SlashCommandListener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        SlashCommandLogger logger = new SlashCommandLogger();
        logger.logEvent(event);       
        commandManager.executeCommand(event);
        
    }
}
