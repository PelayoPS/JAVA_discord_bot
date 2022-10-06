package src.util.commandPattern;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import src.commands.general.*;
import src.commands.mod.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final Map<String, CommandInterface> commands;

    private JDA jda;

    public CommandManager(JDA jda) {
        commands = new HashMap<>();
        registerCommands();
        this.jda = jda;
        eraseOldCommands();
    }

    public CommandInterface getCommand(String name) {
        return commands.get(name);
    }

    public List<CommandData> getCommandDataAll() {
        List<CommandData> commandList = new ArrayList<>();
        for (CommandInterface command : commands.values()){
            commandList.add(command.getSlash());
        }
        return commandList;
    }

    private void registerCommands() {
        //general commands
        commands.put(Avatar.getNameForManagement(), new Avatar());
        commands.put(Help.getNameForManagement(), new Help());
        commands.put(Ping.getNameForManagement(), new Ping());
        commands.put(Repeat.getNameForManagement(), new Repeat());
        commands.put(StreamOnline.getNameForManagement(), new StreamOnline());
        commands.put(UserInfo.getNameForManagement(), new UserInfo());
        commands.put(UpTime.getNameForManagement(), new UpTime());
        //mod commands
        commands.put(Ban.getNameForManagement(), new Ban());
        commands.put(Erase.getNameForManagement(), new Erase());
        commands.put(Kick.getNameForManagement(), new Kick());
        commands.put(Mute.getNameForManagement(), new Mute());
        commands.put(Restart.getNameForManagement(), new Restart());
        commands.put(Warn.getNameForManagement(), new Warn());

    }

    private void eraseOldCommands() {
        jda.retrieveCommands().complete().forEach(command -> eraseCommand(command));
    }

    private void eraseCommand(Command command) {
        if (!commands.containsKey(command.getName())) {
            command.delete().queue();
        }
    }

    public void printCommands() {
        System.out.println("General commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.GENERAL)
                .forEach(command -> System.out.println("\t" + command.getName()));
        System.out.println("Mod commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.MOD)
                .forEach(command -> System.out.println("\t" + command.getName()));
    }

}
