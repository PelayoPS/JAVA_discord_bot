package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Erase extends ListenerAdapter {
    /**
     * When a slash command with the name erase is used this method is called
     * it deletes the amount of messages given in the channel provided
     * @param event
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.MESSAGE_MANAGE)){
            return;
        }
        if (event.getName().equals("erase")) {
            int amountToDelete = event.getOption("amount").getAsInt();
            TextChannel channel = event.getOption("channel").getAsChannel().asTextChannel();
            channel.deleteMessages(getMessagesToDelete(amountToDelete,channel)).queue();
        }
    }

    /**
     * private method to make the code cleaner
     * @param amountToDelete
     * @param channel
     * @return
     */
    private List<Message> getMessagesToDelete(int amountToDelete, TextChannel channel){
        List<Message> messagesToDelete = new ArrayList<Message>();
        messagesToDelete = channel.getHistory().retrievePast(amountToDelete).complete();
        return messagesToDelete;
    }
}
