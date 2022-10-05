package src.commands.mod;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.util.Category;
import src.commands.util.CommandInterface;

import java.util.ArrayList;
import java.util.List;

public class Erase implements CommandInterface {

    private static String name = "erase";

    private Category category = Category.MOD;

    /**
     * When a slash command with the name erase is used this method is called
     * it deletes the amount of messages given in the channel provided
     * @param event
     */
    @Override
    public void handle(SlashCommandInteractionEvent event) {
        if(!event.getMember().getPermissions().contains(Permission.MESSAGE_MANAGE)){
            return;
        }
        TextChannel channel = event.getOption("channel").getAsChannel().asTextChannel();
        int amount = event.getOption("amount").getAsInt();
        List<Message> messages = getMessagesToDelete(amount, channel);
        if(messages.size() < 2) {
            messages.get(0).delete().queue();
        } else {
            channel.deleteMessages(messages).queue();
        }
        event.reply("Deleted " + amount + " messages").queue();
        event.getHook().editOriginal("This message will be deleted in 5 seconds").queueAfter(5, java.util.concurrent.TimeUnit.SECONDS);
        event.getHook().editOriginal("This message will be deleted in 4 seconds").queueAfter(6, java.util.concurrent.TimeUnit.SECONDS);
        event.getHook().editOriginal("This message will be deleted in 3 seconds").queueAfter(7, java.util.concurrent.TimeUnit.SECONDS);
        event.getHook().editOriginal("This message will be deleted in 2 seconds").queueAfter(8, java.util.concurrent.TimeUnit.SECONDS);
        event.getHook().editOriginal("This message will be deleted in 1 seconds").queueAfter(9, java.util.concurrent.TimeUnit.SECONDS);
        event.getHook().deleteOriginal().queueAfter(10, java.util.concurrent.TimeUnit.SECONDS);
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

    @Override
    public CommandData getSlash() {
        CommandData command = Commands.slash(name, "Deletes the amount of messages given in the channel provided")
                .addOption(OptionType.INTEGER, "amount", "The amount of messages to delete", true)
                .addOption(OptionType.CHANNEL, "channel", "The channel to delete the messages from", true);
        return command;
    }

    @Override
    public String getName() {
        return  name;
    }

    @Override
    public Category getCategory() {
        return category;
    }

    public static String getNameForManagement() {
        return name;
    }
}
