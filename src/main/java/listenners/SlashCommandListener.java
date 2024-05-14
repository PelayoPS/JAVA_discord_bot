package listenners;

import main.CommandManager;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SlashCommandListener extends ListenerAdapter {
    private CommandManager commandManager;

    public SlashCommandListener(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event){
        // Prints what command was used and the user that did it in green color
        System.out.println("\u001B[32m" + event.getName() + " was used by " + event.getUser().getName());
        commandManager.executeCommand(event);
        
    }
}
