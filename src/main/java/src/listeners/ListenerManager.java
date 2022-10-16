package src.listeners;

import net.dv8tion.jda.api.hooks.ListenerAdapter;
import src.DiscordBot;
import src.listeners.buttons.ButtonManager;
import src.listeners.serverStats.BoostTier;
import src.listeners.serverStats.GuildEmojisUpdate;

import java.util.ArrayList;
import java.util.List;

public class ListenerManager {

    private List<ListenerAdapter> listeners = new ArrayList<>();

    public ListenerManager(DiscordBot bot) {
        registerListeners(bot);
    }

    private void registerListeners(DiscordBot bot) {
        listeners.add(new OnReady());
        listeners.add(new OnUserJoin(bot));
        listeners.add(new OnUserLeave(bot));
        listeners.add(new OnMessageReceived(bot));
        listeners.addAll(new ButtonManager().getButtonListeners());
        listeners.add(new GuildEmojisUpdate());
        listeners.add(new BoostTier());
    }

    public List<ListenerAdapter> getListener() {
        return listeners;
    }
}
