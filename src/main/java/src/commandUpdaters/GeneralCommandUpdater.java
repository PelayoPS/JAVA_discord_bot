package src.commandUpdaters;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import src.commands.general.*;

import java.util.ArrayList;
import java.util.List;

public class GeneralCommandUpdater {
    private static List<String> generalCommands;
    /**
     * Updates the commands for the general category
     * @return a list of commands
     */
    public static List<CommandData> updateCommands() {
        //command list
        List<CommandData> commandList = new ArrayList<CommandData>();
        commandList.add(//repeat command
                Commands.slash("repeat", "Repeats the content given")
                        .addOption(OptionType.STRING, "content", "The content to repeat", true));
        commandList.add(//avatar command
                Commands.slash("avatar", "Sends the avatar of the user given")
                        .addOption(OptionType.USER, "user", "The user to get the avatar of", true));
        commandList.add(//avatar command
                Commands.slash("userinfo", "Returns a full info of the user given")
                        .addOption(OptionType.USER, "user", "User to get the info", true));
        commandList.add(//ping command
                Commands.slash("ping", "Returns the ping of the bot"));
        commandList.add(//help command
                Commands.slash("help", "Returns a list of commands"));
        commandList.add(//stream online command
                Commands.slash("streamonline", "Returns a list of streamers that are online"));
        //adds the name of each command to the generalCommands list
        generalCommands = new ArrayList<String>();
        for (CommandData command : commandList) {
            generalCommands.add(command.getName());
        }
        return commandList;
    }

    public static List<String> getGeneralCommands() {
        return generalCommands;
    }

    /**
     * Adds the command listeners for the general category
     * @param jda the JDA instance
     */
    public static void addCommandListeners(JDA jda) {
        jda.addEventListener(new Repeat());
        jda.addEventListener(new Avatar());
        jda.addEventListener(new UserInfo());
        jda.addEventListener(new Ping());
        jda.addEventListener(new Help());
        jda.addEventListener(new StreamOnline());

    }
}
