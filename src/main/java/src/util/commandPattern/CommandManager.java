package src.util.commandPattern;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import src.commands.audio.NowPlaying;
import src.commands.audio.VoiceJoin;
import src.commands.audio.VoiceLeave;
import src.commands.audio.controls.*;
import src.commands.games.tictactoe.TicTacToe;
import src.commands.general.*;
import src.commands.general.help.Help;
import src.commands.mod.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandManager {
    private final Map<String, CommandInterface> commands;

    private final JDA jda;

    /**
     * Creates a new CommandManager
     * @param jda
     */
    public CommandManager(JDA jda) {
        commands = new HashMap<>();
        registerCommands();
        this.jda = jda;
        eraseOldCommands();
    }

    /**
     * returns the command with the given name
     * @param name
     * @return
     */
    public CommandInterface getCommand(String name) {
        return commands.get(name);
    }

    /**
     * returns a list of CommandData for all the commands
     * @return
     */
    public List<CommandData> getCommandDataAll() {
        List<CommandData> commandList = new ArrayList<>();
        for (CommandInterface command : commands.values()){
            commandList.add(command.getSlash());
        }
        return commandList;
    }

    /**
     * adds the commands to the commands map
     * key: command name
     * value: command instance
     */
    private void registerCommands() {
        //general commands
        commands.put(Avatar.getNameForManagement(), new Avatar());
        commands.put(Help.getNameForManagement(), new Help(this));
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
        //audio commands
        commands.put(VoiceJoin.getNameForManagement(), new VoiceJoin());
        commands.put(VoiceLeave.getNameForManagement(), new VoiceLeave());
        commands.put(Play.getNameForManagement(), new Play());
        commands.put(Volume.getNameForManagement(), new Volume());
        commands.put(Stop.getNameForManagement(), new Stop());
        commands.put(NowPlaying.getNameForManagement(), new NowPlaying());
        commands.put(Resume.getNameForManagement(), new Resume());
        commands.put(Pause.getNameForManagement(), new Pause());
        commands.put(Rewind.getNameForManagement(), new Rewind());
        commands.put(Next.getNameForManagement(), new Next());
        //game commands
        commands.put(TicTacToe.getNameForManagement(), new TicTacToe());
    }

    /**
     * deletes all the old commands
     */
    private void eraseOldCommands() {
        jda.retrieveCommands().complete().forEach(command -> eraseCommand(command));
    }

    /**
     * deletes the given command
     * @param command
     */
    private void eraseCommand(Command command) {
        if (!commands.containsKey(command.getName())) {
            command.delete().queue();
        }
    }

    /**
     * Prints in the console the commands separated by category
     */
    public void printCommands() {
        System.out.println("General commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.GENERAL)
                .forEach(command -> System.out.println("\t" + command.getName()));
        System.out.println("Mod commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.MOD)
                .forEach(command -> System.out.println("\t" + command.getName()));
        System.out.println("Audio commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.AUDIO)
                .forEach(command -> System.out.println("\t" + command.getName()));
        System.out.println("Game commands loaded: ");
        commands.values().stream()
                .filter(command -> command.getCategory() == Category.GAME)
                .forEach(command -> System.out.println("\t" + command.getName()));
    }

    /**
     * Returns a list of commands for the given category
     * @param category
     * @return
     */
    public List<String> getCommands(Category category) {
        List<String> commandList = new ArrayList<>();
        commands.values().stream()
                .filter(command -> command.getCategory() == category)
                .forEach(command -> commandList.add(command.getName()));
        return commandList;
    }

}
