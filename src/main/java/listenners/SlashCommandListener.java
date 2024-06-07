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
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        // if event is on dms log without guild
        if (event.getGuild() == null) {
            this.commandManager.getLogger().logEvent("LOG|Slash command event: " + event.getName() +
                    " was triggered by " + event.getUser().getName() +
                    " in DMs");
            commandManager.executeCommand(event);
            return;
        }
        this.commandManager.getLogger().logEvent("LOG|Slash command event: " + event.getName() +
                " was triggered by " + event.getUser().getName() +
                " in " + event.getGuild().getName());
        commandManager.executeCommand(event);

    }
}
