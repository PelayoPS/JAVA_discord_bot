package src.listeners;

import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        if (event.getReaction().getEmoji().getName().equals("👍")) {
            event.getChannel().sendMessage("You reacted with a thumbs up.").queue();
        }
        //send private message to the user who reacted
        event.getUser().openPrivateChannel().queue((channel) -> {
            channel.sendMessage("You reacted with " + event.getReaction().getEmoji().getName()).queue();
        });
        System.out.println("user send reaction");
    }
}
